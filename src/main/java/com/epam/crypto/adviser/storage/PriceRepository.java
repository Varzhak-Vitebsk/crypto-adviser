package com.epam.crypto.adviser.storage;

import com.epam.crypto.adviser.storage.model.CryptoEntity;
import com.epam.crypto.adviser.storage.model.PriceEntity;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

  Optional<PriceEntity> findFirstByCryptoAndPriceTimestampBetween(CryptoEntity crypto, Instant from, Instant to,
      Sort order);

}
