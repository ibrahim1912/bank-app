package com.tiger.bankapp.services.responses.accounts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllAccountsResponse {

	private int id;
	private String accountName;
	private String accountNumber;
	private Double balance;
	private int customerId;
	private String customerIdentity;
}
