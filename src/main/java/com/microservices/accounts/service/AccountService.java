package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
  void createAccount(CustomerDto customerDto);
}
