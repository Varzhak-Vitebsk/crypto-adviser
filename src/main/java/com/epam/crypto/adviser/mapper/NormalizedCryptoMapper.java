package com.epam.crypto.adviser.mapper;

import com.epam.crypto.adviser.model.api.NormalizedCrypto;
import com.epam.crypto.adviser.model.dto.PriceDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class NormalizedCryptoMapper {

  @Mapping(target = "crypto", source = "min.crypto.name")
  @Mapping(target = "minPriceTimestamp", source = "min.priceTimestamp")
  @Mapping(target = "maxPriceTimestamp", source = ".", qualifiedByName = "maxPriceInstant")
  @Mapping(target = "normalizedPrice", source = ".", qualifiedByName = "normalize")
  public abstract NormalizedCrypto map(PriceDto min, @Context PriceDto max, @Context int scale);


  @Named("normalize")
  protected BigDecimal normalize(PriceDto min, @Context PriceDto max, @Context int scale) {
    return max.getPrice().subtract(min.getPrice()).setScale(scale, RoundingMode.HALF_DOWN)
        .divide(min.getPrice(), RoundingMode.HALF_DOWN);
  }

  @Named("maxPriceInstant")
  protected Instant maxPriceInstant(PriceDto ignoredMin, @Context PriceDto max) {
    return max.getPriceTimestamp();
  }

}
