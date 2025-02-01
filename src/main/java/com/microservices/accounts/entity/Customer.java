package com.microservices.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Id
  @Column(name = "customer_id")
  private Long customerId;

  private String name;
  private String email;

  @Column(name = "mobile_number")
  private String mobileNumber;
}
