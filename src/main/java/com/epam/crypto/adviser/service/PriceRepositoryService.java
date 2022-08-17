package com.epam.crypto.adviser.service;

import com.epam.crypto.adviser.storage.PriceRepository;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceRepositoryService {

  private final PriceRepository repository;

  void save(PriceEntity entityUpdate) {
    repository.save(entityUpdate);
  }

}
