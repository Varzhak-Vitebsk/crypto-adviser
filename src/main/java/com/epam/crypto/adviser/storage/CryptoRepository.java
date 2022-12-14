package com.epam.crypto.adviser.storage;

import com.epam.crypto.adviser.storage.model.CryptoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {

  Optional<CryptoEntity> findByName(String name);

  List<CryptoEntity> getBySupportedIsTrue();

}
