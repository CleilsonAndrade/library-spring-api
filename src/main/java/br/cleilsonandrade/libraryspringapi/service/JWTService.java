package br.cleilsonandrade.libraryspringapi.service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cleilsonandrade.libraryspringapi.dto.UserLoginDTO;
import br.cleilsonandrade.libraryspringapi.dto.UserTokenDTO;
import br.cleilsonandrade.libraryspringapi.entity.User;
import br.cleilsonandrade.libraryspringapi.repository.UserRepository;
import br.cleilsonandrade.libraryspringapi.service.exception.InvalidLogin;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
  public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  @Autowired
  private UserRepository userRepository;

  public UserTokenDTO authenticateUser(UserLoginDTO userLoginDTO) {
    Optional<User> findUser = this.userRepository.findByEmailAndPassword(userLoginDTO.email(), userLoginDTO.password());

    if (findUser.isEmpty()) {
      throw new InvalidLogin("Invalid access");
    }

    String token = generateToken(userLoginDTO.email());

    return new UserTokenDTO(token);
  }

  private String generateToken(String email) {
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject(email)
        .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
        .setExpiration(timeExpiresToken())
        .compact();
  }

  private Date timeExpiresToken() {
    return Date.from(Instant.now().plusSeconds(60 * 5));
  }

  public String getSubjectByToken(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      throw new SecurityException("Token invalid");
    }

    String token = header.substring(7);

    String subject;

    try {
      JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();

      subject = parser.parseClaimsJws(token).getBody().getSubject();
    } catch (SignatureException exception) {
      throw new SecurityException("Validation error token");
    }

    return subject;
  }
}
