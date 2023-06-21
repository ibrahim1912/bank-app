package com.tiger.bankapp.services.requests.customers;

import lombok.Data;

@Data

public class CreateCustomerRequest {

	private String firstName;
	private String lastName;
	private String identity;
	private int birthYear;
}
