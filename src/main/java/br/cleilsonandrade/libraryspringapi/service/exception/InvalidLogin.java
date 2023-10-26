package br.cleilsonandrade.libraryspringapi.service.exception;

public class InvalidLogin extends RuntimeException {
  public InvalidLogin(String message) {
    super(message);
  }
}
