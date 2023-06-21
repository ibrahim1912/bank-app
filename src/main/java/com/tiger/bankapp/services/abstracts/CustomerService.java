package com.tiger.bankapp.services.abstracts;

import java.util.List;

import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.services.requests.customers.CreateCustomerRequest;
import com.tiger.bankapp.services.requests.customers.DeleteCustomerRequest;
import com.tiger.bankapp.services.requests.customers.UpdateCustomerRequest;
import com.tiger.bankapp.services.responses.customers.GetAllCustomersResponse;
import com.tiger.bankapp.services.responses.customers.GetByCustomerResponse;

public interface CustomerService {

	void createCustomer(CreateCustomerRequest createCustomerRequest);
	void deleteCustomer(DeleteCustomerRequest deleteCustomerRequest);
	void updateCustomer(UpdateCustomerRequest updateCustomerRequest);
	
	GetByCustomerResponse getById(int id);
	List<GetAllCustomersResponse> getAll();
	
	Customer getByCustomerId(int id);
}
