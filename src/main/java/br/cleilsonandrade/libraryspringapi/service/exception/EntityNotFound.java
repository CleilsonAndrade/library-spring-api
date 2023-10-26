package br.cleilsonandrade.libraryspringapi.service.exception;

public class EntityNotFound extends RuntimeException {
  public EntityNotFound(String msg) {
    super(msg);
  }
}