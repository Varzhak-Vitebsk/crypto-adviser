package com.epam.crypto.adviser.exception;

public class SourceFileNotInCSVFormatException extends RuntimeException {

  public SourceFileNotInCSVFormatException(String message) {
    super(message);
  }

  public SourceFileNotInCSVFormatException(String message, Object... args) {
    super(String.format(message, args));
  }
}
