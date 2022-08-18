package com.epam.crypto.adviser.mapper;

import com.epam.crypto.adviser.model.dto.PriceDto;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PriceEntityToPriceDtoMapper {

  List<PriceDto> map(List<PriceEntity> source);

  PriceDto map(PriceEntity source);

}
