package br.cleilsonandrade.libraryspringapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cleilsonandrade.libraryspringapi.dto.UserRegisterDTO;
import br.cleilsonandrade.libraryspringapi.entity.User;
import br.cleilsonandrade.libraryspringapi.repository.UserRepository;
import br.cleilsonandrade.libraryspringapi.service.exception.UserAlreadyExists;
import br.cleilsonandrade.libraryspringapi.service.exception.UserDoesNotExist;
import br.cleilsonandrade.libraryspringapi.service.exception.UserDoesNotHavePermission;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTService jwtService;

  public User registerUser(UserRegisterDTO userRegisterDTO) {
    Optional<User> findUser = this.userRepository.findByEmail(userRegisterDTO.email());

    if (findUser.isPresent()) {
      throw new UserAlreadyExists("User already exists in the system");
    }

    User newUser = new User(userRegisterDTO);

    this.userRepository.save(newUser);

    return newUser;
  }

  private boolean userHasPermission(String header, String email) {
    String userToken = this.jwtService.getSubjectByToken(header);
    Optional<User> findUser = this.userRepository.findByEmail(userToken);

    return findUser.isPresent() && findUser.get().getEmail().equals(email);
  }

  public User findUser(String email, String header) {
    Optional<User> findUser = this.userRepository.findByEmail(email);

    if (findUser.isPresent()) {
      throw new UserDoesNotExist("User does not exist");
    }

    if (!userHasPermission(header, email)) {
      throw new UserDoesNotHavePermission("The user does not have access permission");
    }

    return findUser.get();
  }

  public void deleteUser(String email, String header) {
    Optional<User> findUser = this.userRepository.findByEmail(email);

    if (findUser.isPresent()) {
      throw new UserDoesNotExist("User does not exist");
    }

    if (!userHasPermission(header, email)) {
      throw new UserDoesNotHavePermission("The user does not have access permission");
    }

    this.userRepository.delete(findUser.get());
  }

}
