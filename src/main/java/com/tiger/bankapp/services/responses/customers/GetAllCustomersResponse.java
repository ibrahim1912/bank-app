package com.tiger.bankapp.services.responses.customers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllCustomersResponse {

	private int id;
	private String firstName;
	private String lastName;
	private String identity;
	private int birthYear;
}
