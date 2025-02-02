package com.microservices.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
  private Long accountNumber;

  private String accountType;

  private String branchAddress;

  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  // Diğer getter ve setter metodları
  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getBranchAddress() {
    return branchAddress;
  }

  public void setBranchAddress(String branchAddress) {
    this.branchAddress = branchAddress;
  }
}
