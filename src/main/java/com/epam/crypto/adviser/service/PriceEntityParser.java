package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.exception.CsvParseException;
import com.epam.crypto.adviser.exception.NotSupportedCryptoException;
import com.epam.crypto.adviser.model.CSVRecordParsingContext;
import com.epam.crypto.adviser.model.CSVRecordValidationContext;
import com.epam.crypto.adviser.model.PriceCSVRecordParsingContext;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import com.epam.crypto.adviser.utility.CSVUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PriceEntityParser implements CSVRecordParser<PriceEntity> {

  private static final String NAME_FIELD = "symbol";
  private static final String TIMESTAMP_FIELD = "timestamp";
  private static final String PRICE_FIELD = "price";
  private static final Set<String> MANDATORY_FIELDS = Set.of(NAME_FIELD, TIMESTAMP_FIELD, PRICE_FIELD);
  private final CSVRecordValidator recordValidator;
  @Override
  public PriceEntity parse(CSVRecordParsingContext parsingContext) throws CsvParseException {

    var source = parsingContext.getSource();
    List<CryptoEntity> supportedCryptos;

    if (parsingContext instanceof PriceCSVRecordParsingContext context) {
      supportedCryptos = context.getSupportedCryptos();
    } else {
      throw new CsvParseException("Parsing context is not an instance of PriceCSVRecordParsingContext.class!");
    }

    if (recordValidator.isInvalid(makeContext(source))) {
      throw new CsvParseException("One of mandatory or not blank fields is absent or blank!");
    }
    return PriceEntity.builder()
        .crypto(parseName(source.get(NAME_FIELD), supportedCryptos))
        .price(parsePrice(source.get(PRICE_FIELD)))
        .priceTimestamp(parseTimestamp(source.get(TIMESTAMP_FIELD)))
        .build();
  }

  private CSVRecordValidationContext makeContext(CSVRecord source) {
    return CSVRecordValidationContext.builder()
        .csvRecord(source)
        .mandatoryFields(MANDATORY_FIELDS)
        .notBlankFields(MANDATORY_FIELDS)
        .build();
  }

  private CryptoEntity parseName(String value, List<CryptoEntity> supportedCryptos) {
    return ListUtils.emptyIfNull(supportedCryptos).stream()
        .filter(entity -> value.equals(entity.getName()))
        .findFirst()
        .orElseThrow(() -> new NotSupportedCryptoException("Crypto %s is not supported!".formatted(value)));
  }

  private BigDecimal parsePrice(String value) {
    try {
      return CSVUtils.parseStringToBigDecimal(value);
    } catch (NumberFormatException e) {
      throw new CsvParseException("Failed to parse price %s!".formatted(value));
    }
  }

  private Instant parseTimestamp(String value) {
    try {
      return CSVUtils.parseStringToInstant(value);
    } catch (DateTimeException | NumberFormatException e) {
      throw new CsvParseException("Failed to parse timestamp %s!".formatted(value));
    }
  }
}
