package com.tiger.bankapp.services.requests.accounts;

import javax.validation.constraints.Positive;

import lombok.Data;

@Data

public class CreateMoneyTransferRequest {

	@Positive
	private int fromId;
	
	@Positive
	private int toId;
	
	@Positive
	private Double amount;
}
