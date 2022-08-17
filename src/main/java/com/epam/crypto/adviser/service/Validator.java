package com.epam.crypto.adviser.service;

public interface Validator<T> {

  boolean isValid(T source);

  default boolean isInvalid(T source) {
    return !isValid(source);
  }
}
