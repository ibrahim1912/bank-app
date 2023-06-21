package com.tiger.bankapp.services.requests.accounts;

import lombok.Data;

@Data

public class CreateAccountRequest {
	
	private String accountName;
	private Double balance;
	private int customerId;
	

}
