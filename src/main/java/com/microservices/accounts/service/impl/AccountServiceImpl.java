package com.microservices.accounts.service.impl;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.CustomerAlreadyExistException;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.AccountService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;

  // Constructor injection (manuel olarak)
  public AccountServiceImpl(
      AccountsRepository accountsRepository, CustomerRepository customerRepository) {
    this.accountsRepository = accountsRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistException(
          "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
    }
    customer.setCreatedBy("Admin");
    customer.setUpdatedAt(LocalDateTime.now());
    customer.setCreatedAt(LocalDateTime.now());

    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());

    // Hesap numarasını oluştur
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
    newAccount.setAccountNumber(randomAccNumber);

    // Hesap türü
    newAccount.setAccountType(AccountsConstants.SAVINGS);

    newAccount.setBranchAddress("123 Main Street, New York"); // Branch adresi burada atanıyor.

    // Zaman damgaları
    newAccount.setCreatedAt(LocalDateTime.now());
    newAccount.setCreatedBy("Admin");

    return newAccount;
  }
}
