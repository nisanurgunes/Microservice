package com.microservices.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column(updatable = false)
  private LocalDateTime updatedAt;

  @Column(insertable = false)
  private String createdBy;

  @Column(insertable = false)
  private String updatedBy;
}
