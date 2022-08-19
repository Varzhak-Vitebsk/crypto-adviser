package com.epam.crypto.adviser.exception;

public class NotSupportedCryptoException extends RuntimeException {

  public NotSupportedCryptoException(String message) {
    super(message);
  }

  public NotSupportedCryptoException(String message, Object... args) {
    super(String.format(message, args));
  }
}
