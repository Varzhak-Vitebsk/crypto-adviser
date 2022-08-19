package com.epam.crypto.adviser.exception;

public class PriceNotFoundException extends RuntimeException {

  public PriceNotFoundException(String message) {
    super(message);
  }

  public PriceNotFoundException(String message, Object... args) {
    super(String.format(message, args));
  }
}
