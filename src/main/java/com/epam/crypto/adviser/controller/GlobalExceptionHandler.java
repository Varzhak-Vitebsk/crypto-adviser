package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.exception.SourceFileNotInCSVFormatException;
import com.epam.crypto.adviser.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.Instant;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String ERROR_MESSAGE = "Exception has occurred: ";

  @ExceptionHandler({DateTimeException.class})
  public ResponseEntity<ErrorResponse> handleInternalServerException(Exception ex,
      HttpServletRequest request) {
    log.error(ERROR_MESSAGE, ex);
    return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler({SourceFileNotInCSVFormatException.class, CsvParseException.class})
  public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex,
      HttpServletRequest request) {
    log.error(ERROR_MESSAGE, ex);
    return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
  }

  private ResponseEntity<ErrorResponse> buildResponse(final String message, HttpStatus httpStatus,
      HttpServletRequest request) {
    final var body = ErrorResponse.builder()
        .timestamp(Instant.now())
        .status(httpStatus.value())
        .error(message)
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.status(httpStatus)
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
  }

}
