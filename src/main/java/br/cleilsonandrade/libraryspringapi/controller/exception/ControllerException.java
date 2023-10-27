package br.cleilsonandrade.libraryspringapi.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.cleilsonandrade.libraryspringapi.service.exception.EntityNotFound;
import br.cleilsonandrade.libraryspringapi.service.exception.InvalidLogin;
import br.cleilsonandrade.libraryspringapi.service.exception.UserAlreadyExists;
import br.cleilsonandrade.libraryspringapi.service.exception.UserDoesNotExist;
import br.cleilsonandrade.libraryspringapi.service.exception.UserDoesNotHavePermission;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerException {
  @ExceptionHandler(EntityNotFound.class)
  public ResponseEntity<ErrorResponse> notFound(EntityNotFound entityNotFound, HttpServletRequest request) {
    String error = "Not found";
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(UserAlreadyExists.class)
  public ResponseEntity<ErrorResponse> userAlreadyExistsError(UserAlreadyExists entityNotFound,
      HttpServletRequest request) {
    String error = "User already exists";
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(UserDoesNotExist.class)
  public ResponseEntity<ErrorResponse> userDoesNotExistError(UserDoesNotExist entityNotFound,
      HttpServletRequest request) {
    String error = "User does not exist";
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvalidLogin.class)
  public ResponseEntity<ErrorResponse> invalidLoginError(InvalidLogin entityNotFound,
      HttpServletRequest request) {
    String error = "Invalid Login";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(UserDoesNotHavePermission.class)
  public ResponseEntity<ErrorResponse> userDoesNotHavePermissionError(UserDoesNotHavePermission entityNotFound,
      HttpServletRequest request) {
    String error = "User does not have permission";
    HttpStatus status = HttpStatus.FORBIDDEN;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(SecurityException.class)
  public ResponseEntity<ErrorResponse> securityExceptionTokenError(SecurityException entityNotFound,
      HttpServletRequest request) {
    String error = "Token invalid";
    HttpStatus status = HttpStatus.FORBIDDEN;
    ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, entityNotFound.getMessage(),
        request.getRequestURI());
    request.getRequestURI();

    return ResponseEntity.status(status).body(err);
  }
}
