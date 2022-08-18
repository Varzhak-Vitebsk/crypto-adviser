package com.epam.crypto.adviser.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.model.CryptoCSVRecordParsingContext;
import com.epam.crypto.adviser.service.parser.CryptoEntityParser;
import com.epam.crypto.adviser.service.utility.TestUtils;
import com.epam.crypto.adviser.service.validator.CSVRecordValidator;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

class CryptoEntityParserTest {

  private final CryptoEntityParser parser = new CryptoEntityParser(new CSVRecordValidator());

  @Test
  void parse_MissingName_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Cryptos_no_name_field.csv")) {
      //Given: invalid record
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class, () -> parser.parse(makeContext(records.iterator().next())));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_BlankSupportedStatus_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Cryptos_no_status.csv")) {
      //Given: invalid record
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class, () -> parser.parse(makeContext(records.iterator().next())));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_ValidData_EqualsToExample() {
    try (var reader = TestUtils.reader("classpath:Cryptos_valid.csv")) {
      //Given: valid record
      var records = TestUtils.loadResource(reader);

      //Then: service returns parsed entity
      assertThat(makeExample()).usingRecursiveComparison()
          .isEqualTo(parser.parse(makeContext(records.iterator().next())));
    } catch (IOException ignored) {
    }
  }

  private CryptoEntity makeExample() {
    return CryptoEntity.builder()
        .name("BTC")
        .supported(true)
        .prices(Set.of())
        .build();
  }

  private CryptoCSVRecordParsingContext makeContext(CSVRecord source) {
    return CryptoCSVRecordParsingContext.builder()
        .source(source)
        .build();
  }
}