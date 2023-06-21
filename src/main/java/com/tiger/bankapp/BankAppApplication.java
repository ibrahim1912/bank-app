package com.tiger.bankapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tiger.bankapp.common.utilities.exceptions.BusinessException;
import com.tiger.bankapp.common.utilities.exceptions.ProblemDetails;
import com.tiger.bankapp.common.utilities.exceptions.ValidationProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class BankAppApplication {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setValidationErrors(validationErrors);
		validationProblemDetails.setMessage("Validation Errors");
		return validationProblemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessExceptions(BusinessException businessException) {
		ProblemDetails details = new ProblemDetails();
		details.setMessage(businessException.getMessage());
		return details;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
