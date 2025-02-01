package com.microservices.accounts.exception;

import com.microservices.accounts.dto.ErrorResponseDto;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomerAlreadyExistException.class)
  public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(
      CustomerAlreadyExistException exception, WebRequest webRequest) {
    ErrorResponseDto errorResponseDto =
        new ErrorResponseDto(
            webRequest.getDescription(false),
            HttpStatus.BAD_REQUEST,
            LocalDateTime.now(),
            exception.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
  }
}
