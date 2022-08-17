package com.epam.crypto.adviser.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * Super entity
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

  /**
   * Record create time
   */
  @CreationTimestamp
  private Instant createDate;

  /**
   * Record lat update time
   */
  @UpdateTimestamp
  private Instant updateDate;

}
