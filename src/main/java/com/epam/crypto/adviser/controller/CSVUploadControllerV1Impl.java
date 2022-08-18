package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.exception.SourceFileNotInCSVFormatException;
import com.epam.crypto.adviser.service.validator.CSVFileValidator;
import com.epam.crypto.adviser.service.loader.CryptoPricesFromCSVDataLoader;
import com.epam.crypto.adviser.service.loader.CryptosFromCSVDataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CSVUploadControllerV1Impl implements CSVUploadControllerV1 {

  private final CSVFileValidator csvFileValidator;
  private final CryptoPricesFromCSVDataLoader cryptoPricesFromCsvDataLoader;
  private final CryptosFromCSVDataLoader cryptosFromCsvDataLoader;

  @Override
  public ResponseEntity<Void> uploadCryptoPrices(MultipartFile file) {
    validateRequest(file);
    cryptoPricesFromCsvDataLoader.uploadData(file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> uploadCryptos(MultipartFile file) {
    validateRequest(file);
    cryptosFromCsvDataLoader.uploadData(file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  private void validateRequest(MultipartFile file) {
    if (csvFileValidator.isInvalid(file)) {
      throw new SourceFileNotInCSVFormatException("Uploaded file is not in SCV format");
    }
  }
}
