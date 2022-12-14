package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.storage.CryptoRepository;
import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoRepositoryService {

  private final CryptoRepository repository;

  public void updateOrCreate(CryptoEntity entityUpdate) {
    var entity = repository.findByName(entityUpdate.getName())
        .map(cryptoEntity -> {
          cryptoEntity.setSupported(entityUpdate.isSupported());
          return cryptoEntity;
        }).orElse(entityUpdate);
    repository.save(entity);
  }

  public List<CryptoEntity> getSupportedCryptos() {
    return repository.getBySupportedIsTrue();
  }

}
