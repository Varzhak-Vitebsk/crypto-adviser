package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.storage.CryptoRepository;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CryptoRepositoryServiceTest {

  private static final String CRYPTO_NAME = "BTC";

  @Mock
  private CryptoRepository repository;
  @InjectMocks
  private CryptoRepositoryService service;

  @Test
  void updateOrCreate_ExistedEntity_EntityUpdated() {
    //given source and entity
    var example = CryptoEntity.builder()
        .name(CRYPTO_NAME)
        .supported(true)
        .build();

    var source = CryptoEntity.builder()
        .name(CRYPTO_NAME)
        .supported(true)
        .build();

    //when findByName called - return entity
    Mockito.when(repository.findByName(CRYPTO_NAME))
        .thenReturn(Optional.of(CryptoEntity.builder()
            .name(CRYPTO_NAME)
            .supported(false)
            .build()));

    service.updateOrCreate(source);

    //then save should be called with proper entity

    Mockito.verify(repository, Mockito.times(1))
        .save(Mockito.argThat(entity -> matches(entity, example)));
  }

  @Test
  void updateOrCreate_NoExistedEntity_EntitySaved() {
    //given source and entity
    var example = CryptoEntity.builder()
        .name(CRYPTO_NAME)
        .supported(false)
        .build();

    var source = CryptoEntity.builder()
        .name(CRYPTO_NAME)
        .supported(false)
        .build();

    //when findByName called - return empty optional
    Mockito.when(repository.findByName(CRYPTO_NAME))
        .thenReturn(Optional.empty());

    service.updateOrCreate(source);

    //then save should be called with proper entity

    Mockito.verify(repository, Mockito.times(1))
        .save(Mockito.argThat(entity -> matches(entity, example)));
  }

  private boolean matches(CryptoEntity source, CryptoEntity example) {
    return example.isSupported() == source.isSupported()
        && example.getName().equals(source.getName());
  }


}