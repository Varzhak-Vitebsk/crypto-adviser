package com.epam.crypto.adviser.storage;

import com.epam.crypto.adviser.storage.model.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {

  Optional<CryptoEntity> findByName(String name);

}
