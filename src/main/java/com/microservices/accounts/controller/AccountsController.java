package com.microservices.accounts.controller;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    path = "/api",
    produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

  private final AccountService accountService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }

  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchAccountDetails(
      @RequestParam
          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
          String mobileNumber) {
    CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountService.updateAccount(customerDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto((AccountsConstants.STATUS_200), AccountsConstants.MESSAGE_200));

    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deteleAccountDetails(
      @RequestParam
          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
          String mobileNumber) {
    boolean isDeleted = accountService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
  }
}
