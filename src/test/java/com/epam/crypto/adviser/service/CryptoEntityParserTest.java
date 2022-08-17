package com.epam.crypto.adviser.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.service.utility.TestUtils;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CryptoEntityParserTest {

  private final CryptoEntityParser parser = new CryptoEntityParser(new CSVRecordValidator());

  @Test
  void parse_MissingName_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Cryptos_no_name_field.csv")) {
      //Given: invalid file
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class, () -> parser.parse(records.iterator().next()));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_MissingSupportedStatus_ThrowsException() {
    try (var reader = TestUtils.reader("classpath:Cryptos_no_status.csv")) {
      //Given: invalid file
      var records = TestUtils.loadResource(reader);

      //Then: service throws exception
      assertThrows(CsvParseException.class, () -> parser.parse(records.iterator().next()));
    } catch (IOException ignored) {
    }
  }

  @Test
  void parse_ValidData_EqualsToExample() {
    try (var reader = TestUtils.reader("classpath:Cryptos_valid.csv")) {
      //Given: valid file
      var records = TestUtils.loadResource(reader);

      //Then: service returns parsed entity
      assertThat(makeExample()).usingRecursiveComparison().isEqualTo(parser.parse(records.iterator().next()));
    } catch (IOException ignored) {
    }
  }

  CryptoEntity makeExample() {
    return CryptoEntity.builder()
        .name("BTC")
        .supported(true)
        .prices(Set.of())
        .build();
  }
}