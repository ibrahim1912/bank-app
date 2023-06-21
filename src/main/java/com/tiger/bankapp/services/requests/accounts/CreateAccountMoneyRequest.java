package com.tiger.bankapp.services.requests.accounts;

import lombok.Data;

@Data
public class CreateAccountMoneyRequest {

	private int id;
	private Double amount;
}
