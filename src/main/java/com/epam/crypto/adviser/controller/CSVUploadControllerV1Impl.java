package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.service.CryptoPricesFromCSVDataLoader;
import com.epam.crypto.adviser.service.CryptosFromCSVDataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CSVUploadControllerV1Impl implements CSVUploadControllerV1 {

  private final CryptoPricesFromCSVDataLoader cryptoPricesFromCsvDataLoader;
  private final CryptosFromCSVDataLoader cryptosFromCsvDataLoader;

  @Override
  public ResponseEntity<Void> uploadCryptoPrices(MultipartFile file) {
    cryptoPricesFromCsvDataLoader.uploadData(file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> uploadCryptos(MultipartFile file) {
    cryptosFromCsvDataLoader.uploadData(file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
