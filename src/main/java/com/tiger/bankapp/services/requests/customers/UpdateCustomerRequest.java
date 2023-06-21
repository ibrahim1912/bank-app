package com.tiger.bankapp.services.requests.customers;

import lombok.Data;

@Data

public class UpdateCustomerRequest {

	private int id;
	private String firstName;
	private String lastName;
	private String identity;
	private int birthYear;
}
