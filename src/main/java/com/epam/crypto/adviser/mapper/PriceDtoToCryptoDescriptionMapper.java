package com.epam.crypto.adviser.mapper;

import com.epam.crypto.adviser.model.api.CryptoDescription;
import com.epam.crypto.adviser.model.dto.PriceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PriceDtoToCryptoDescriptionMapper {

  @Mapping(target = "timestamp", source = "priceTimestamp")
  CryptoDescription map(PriceDto source);

}
