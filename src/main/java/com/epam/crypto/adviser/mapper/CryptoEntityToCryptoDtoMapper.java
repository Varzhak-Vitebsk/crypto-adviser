package com.epam.crypto.adviser.mapper;

import com.epam.crypto.adviser.model.dto.CryptoDto;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CryptoEntityToCryptoDtoMapper {

  List<CryptoDto> map(List<CryptoEntity> source);

  CryptoDto map(CryptoEntity source);

}
