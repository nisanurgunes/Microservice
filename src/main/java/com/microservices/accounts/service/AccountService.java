package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface AccountService {
  void createAccount(CustomerDto customerDto);
}
