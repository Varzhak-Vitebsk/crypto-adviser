package com.epam.crypto.adviser.controller;

import com.epam.crypto.adviser.service.CryptoFromCsvDataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CsvUploadControllerV1Impl implements CsvUploadControllerV1 {

  private final CryptoFromCsvDataLoader cryptoFromCsvDataLoader;

  @Override
  public ResponseEntity<Void> uploadCryptoValues(MultipartFile file) {
    cryptoFromCsvDataLoader.uploadData(file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
