package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.exception.NotSupportedCryptoException;
import com.epam.crypto.adviser.model.PriceCSVRecordParsingContext;
import com.epam.crypto.adviser.service.parser.PriceEntityParser;
import com.epam.crypto.adviser.service.utility.TestUtils;
import com.epam.crypto.adviser.service.validator.CSVRecordValidator;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PriceEntityParserTest {

  private static final String CRYPTO_NAME = "BTC";
  private final PriceEntityParser parser = new PriceEntityParser(new CSVRecordValidator());

  @Test
  void parse_MissingName_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Price_no_symbol_field.csv")) {
      //Given: invalid record
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class,
          () -> parser.parse(makeContext(records.iterator().next(), getSupportedCryptos())));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_BlankTimestamp_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Price_no_timestamp.csv")) {
      //Given: invalid record
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class,
          () -> parser.parse(makeContext(records.iterator().next(), getSupportedCryptos())));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_NotSupportedCrypto_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Price_valid.csv")) {
      //Given: invalid record
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(NotSupportedCryptoException.class,
          () -> parser.parse(makeContext(records.iterator().next(), null)));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_ValidData_EqualsToExample() {
    try (var reader = TestUtils.reader("classpath:Price_valid.csv")) {
      //Given: valid file
      var records = TestUtils.loadResource(reader);

      //Then: service returns parsed entity
      assertThat(makeExample()).usingRecursiveComparison()
          .isEqualTo(parser.parse(makeContext(records.iterator().next(), getSupportedCryptos())));
    } catch (IOException ignored) {
    }
  }

  private PriceEntity makeExample() {
    return PriceEntity.builder()
        .priceTimestamp(Instant.ofEpochMilli(Long.parseLong("1641009600000")))
        .price(BigDecimal.valueOf(46813.21D))
        .crypto(makeCrypto())
        .build();
  }

  private CryptoEntity makeCrypto() {
    return CryptoEntity.builder()
        .name(CRYPTO_NAME)
        .supported(true)
        .prices(Set.of())
        .build();
  }

  private PriceCSVRecordParsingContext makeContext(CSVRecord source, List<CryptoEntity> supportedCryptos) {
    return PriceCSVRecordParsingContext.builder()
        .source(source)
        .supportedCryptos(supportedCryptos)
        .build();
  }

  private List<CryptoEntity> getSupportedCryptos() {
    return List.of(makeCrypto());
  }
}