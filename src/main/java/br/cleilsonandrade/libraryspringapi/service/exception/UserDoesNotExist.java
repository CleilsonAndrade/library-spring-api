package br.cleilsonandrade.libraryspringapi.service.exception;

public class UserDoesNotExist extends RuntimeException {
  public UserDoesNotExist(String message) {
    super(message);
  }
}
