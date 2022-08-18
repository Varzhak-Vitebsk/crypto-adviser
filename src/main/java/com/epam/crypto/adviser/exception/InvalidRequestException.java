package com.epam.crypto.adviser.exception;

public class InvalidRequestException extends RuntimeException {

  public InvalidRequestException(String message) {
    super(message);
  }

  public InvalidRequestException(String message, Object... args) {
    super(String.format(message, args));
  }
}
