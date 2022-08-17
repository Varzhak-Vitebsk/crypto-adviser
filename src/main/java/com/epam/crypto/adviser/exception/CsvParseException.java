package com.epam.crypto.adviser.exception;

public class CsvParseException extends RuntimeException {

  public CsvParseException(String message) {
    super(message);
  }

  public CsvParseException(String message, Object... args) {
    super(String.format(message, args));
  }
}
