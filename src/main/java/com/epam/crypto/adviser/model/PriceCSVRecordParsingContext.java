package com.epam.crypto.adviser.model;

import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public final class PriceCSVRecordParsingContext extends CSVRecordParsingContext {

  private final List<CryptoEntity> supportedCryptos;

}
