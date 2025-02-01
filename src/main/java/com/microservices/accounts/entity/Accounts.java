package com.microservices.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
  @Column(name = "customer_id")
  private Long customerId;

  @Id
  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "branch_adress")
  private String branchAddress;
}
