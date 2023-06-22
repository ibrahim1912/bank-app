package com.tiger.bankapp.services.requests.accounts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data

public class CreateAccountRequest {
	
	@NotEmpty
	@NotBlank
	@Size(min=3,max=20)
	private String accountName;
	
	@Positive
	private Double balance;
	
	@Positive
	private int customerId;
	

}


