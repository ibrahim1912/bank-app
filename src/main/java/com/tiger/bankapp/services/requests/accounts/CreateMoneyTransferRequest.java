package com.tiger.bankapp.services.requests.accounts;

import lombok.Data;

@Data

public class CreateMoneyTransferRequest {

	private int fromId;
	private int toId;
	private Double amount;
}
