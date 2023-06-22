package com.tiger.bankapp.services.requests.accounts;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data

public class UpdateAccountRequest {

	@Positive
	private int id;
	
	@Size(min=3,max=20)
	private String accountName;
	
}
