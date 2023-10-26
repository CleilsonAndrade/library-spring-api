package br.cleilsonandrade.libraryspringapi.service.exception;

public class UserDoesNotHavePermission extends RuntimeException {
  public UserDoesNotHavePermission(String message) {
    super(message);
  }
}
