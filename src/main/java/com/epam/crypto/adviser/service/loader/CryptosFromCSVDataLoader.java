package com.epam.crypto.adviser.service.loader;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.model.CryptoCSVRecordParsingContext;
import com.epam.crypto.adviser.service.CryptoRepositoryService;
import com.epam.crypto.adviser.service.parser.CryptoEntityParser;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptosFromCSVDataLoader implements DataLoader {

  private final CryptoEntityParser cryptoEntityParser;
  private final CryptoRepositoryService cryptoRepositoryService;

  @Override
  public void uploadData(MultipartFile file) {
    try {
      Reader reader = getReader(file);
      loadCryptos(reader);
      log.info("File {} uploaded successfully", file.getOriginalFilename());
    } catch (CsvParseException e) {
      throw new CsvParseException(e.getMessage() + ". File: " + file.getOriginalFilename());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Reader getReader(MultipartFile file) throws IOException {
    return new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
  }

  private void loadCryptos(Reader reader) throws IOException {
    Iterable<CSVRecord> records;
    try {
      records = CSVFormat.RFC4180.builder().setHeader().build().parse(reader);
    } catch (IllegalArgumentException e) {
      throw new CsvParseException("Failed to parse file: {}", e.getMessage());
    }

    for (CSVRecord csvRecord : records) {
      CryptoEntity parsedCrypto;
      try {
        parsedCrypto = cryptoEntityParser.parse(makeContext(csvRecord));
      } catch (CsvParseException e) {
        log.error("Failed to parse crypto record {}: {}", csvRecord, e.getMessage());
        continue;
      }
      cryptoRepositoryService.updateOrCreate(parsedCrypto);
    }
  }

  private CryptoCSVRecordParsingContext makeContext(CSVRecord source) {
    return CryptoCSVRecordParsingContext.builder()
        .source(source)
        .build();
  }

}
