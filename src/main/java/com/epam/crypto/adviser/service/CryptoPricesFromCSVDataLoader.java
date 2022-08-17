package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.exception.SourceFileNotInCSVFormatException;
import com.epam.crypto.adviser.model.PriceCSVRecordParsingContext;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoPricesFromCSVDataLoader implements DataLoader {

  private final CSVFileValidator csvFileValidator;
  private final PriceEntityParser priceEntityParser;
  private final PriceRepositoryService priceRepositoryService;
  private final CryptoRepositoryService cryptoRepositoryService;

  @Override
  public void uploadData(MultipartFile file) {
    if (csvFileValidator.isInvalid(file)) {
      throw new SourceFileNotInCSVFormatException("Uploaded file is not in SCV format");
    }
    try {
      Reader reader = getReader(file);
      loadPrices(reader);
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

  private void loadPrices(Reader reader) throws IOException {
    Iterable<CSVRecord> records;
    try {
      records = CSVFormat.RFC4180.builder().setHeader().build().parse(reader);
    } catch (IllegalArgumentException e) {
      throw new CsvParseException("Failed to parse file: {}", e.getMessage());
    }

    var supportedCryptos = cryptoRepositoryService.getSupportedCryptos();

    for (CSVRecord csvRecord : records) {
      PriceEntity parsedPrice;
      try {
        parsedPrice = priceEntityParser.parse(makeContext(csvRecord, supportedCryptos));
      } catch (CsvParseException e) {
        log.error("Failed to parse crypto record {}: {}", csvRecord, e.getMessage());
        continue;
      }
      priceRepositoryService.save(parsedPrice);
    }
  }

  private PriceCSVRecordParsingContext makeContext(CSVRecord source, List<CryptoEntity> supportedCryptos) {
    return PriceCSVRecordParsingContext.builder()
        .source(source)
        .supportedCryptos(supportedCryptos)
        .build();
  }
}
