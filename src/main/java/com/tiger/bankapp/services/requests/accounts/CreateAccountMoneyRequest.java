package com.tiger.bankapp.services.requests.accounts;

import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class CreateAccountMoneyRequest {

	@Positive
	private int id;
	
	@Positive
	private Double amount;
}
