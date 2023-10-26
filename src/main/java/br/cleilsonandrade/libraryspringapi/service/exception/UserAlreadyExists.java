package br.cleilsonandrade.libraryspringapi.service.exception;

public class UserAlreadyExists extends RuntimeException {
  public UserAlreadyExists(String message) {
    super(message);
  }
}
