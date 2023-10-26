package br.cleilsonandrade.libraryspringapi.filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import br.cleilsonandrade.libraryspringapi.service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterToken extends GenericFilterBean {

  public static final int TOKEN_KEY = 7;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;

    String header = servletRequest.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or poorly formatted token");
      return;
    }

    String token = header.substring(TOKEN_KEY);

    try {
      JwtParser parser = Jwts.parserBuilder().setSigningKey(JWTService.TOKEN_KEY).build();
      parser.parseClaimsJws(token).getBody();

    } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | PrematureJwtException
        | IllegalArgumentException | ExpiredJwtException exception) {
      servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
      return;
    }

    chain.doFilter(request, response);
  }

}
