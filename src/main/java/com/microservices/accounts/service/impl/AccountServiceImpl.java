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
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  AccountsRepository accountsRepository;
  CustomerRepository customerRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());

    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistException(
          "Customer already exist" + customerDto.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccounts(savedCustomer));
  }

  private Accounts createNewAccounts(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    return newAccount;
  }
}
