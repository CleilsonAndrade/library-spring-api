package br.cleilsonandrade.libraryspringapi.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.cleilsonandrade.libraryspringapi.service.exception.EntityNotFound;
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
}
