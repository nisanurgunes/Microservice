package com.microservices.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDto {
  @NotEmpty(message = "Name should not be empty or null")
  @Size(min = 5, max = 30, message = "The lenght of the customer name should be between 5 and 30")
  private String name;

  @NotEmpty(message = "Email should not be empty or null")
  @Email(message = "Email adress should be valid value")
  private String email;

  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  private String mobileNumber;

  private AccountsDto accountsDto;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public AccountsDto getAccountsDto() {
    return accountsDto;
  }

  public void setAccountsDto(AccountsDto accountsDto) {
    this.accountsDto = accountsDto;
  }
}
