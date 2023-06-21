package com.tiger.bankapp.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.repositories.CustomerRepository;
import com.tiger.bankapp.services.abstracts.CustomerService;
import com.tiger.bankapp.services.requests.customers.CreateCustomerRequest;
import com.tiger.bankapp.services.requests.customers.DeleteCustomerRequest;
import com.tiger.bankapp.services.requests.customers.UpdateCustomerRequest;
import com.tiger.bankapp.services.responses.customers.GetAllCustomersResponse;
import com.tiger.bankapp.services.responses.customers.GetByCustomerResponse;
import com.tiger.bankapp.services.rules.CustomerBusinessRules;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerBusinessRules customerBusinessRules;

	@Override
	public void createCustomer(CreateCustomerRequest createCustomerRequest) {
		customerBusinessRules.checkIfCustomerExists(createCustomerRequest.getIdentity());

		Customer customer = Customer.builder().firstName(createCustomerRequest.getFirstName())
				.lastName(createCustomerRequest.getLastName()).identity(createCustomerRequest.getIdentity())
				.birthYear(createCustomerRequest.getBirthYear()).build();
		this.customerRepository.save(customer);

	}

	@Override
	public void deleteCustomer(DeleteCustomerRequest deleteCustomerRequest) {
		customerBusinessRules.checkIfCustomerIdExists(deleteCustomerRequest.getId());

		this.customerRepository.deleteById(deleteCustomerRequest.getId());

	}

	@Override
	public void updateCustomer(UpdateCustomerRequest updateCustomerRequest) {
		customerBusinessRules.checkIfCustomerIdExists(updateCustomerRequest.getId());
		customerBusinessRules.checkIfIdentityIsSameForUpdate(updateCustomerRequest.getId(),updateCustomerRequest.getIdentity());
		
		Customer customer = Customer.builder()
				.id(updateCustomerRequest.getId())
				.firstName(updateCustomerRequest.getFirstName())
				.lastName(updateCustomerRequest.getLastName())
				.identity(updateCustomerRequest.getIdentity())
				.birthYear(updateCustomerRequest.getBirthYear())
				.build();
		this.customerRepository.save(customer);

	}

	@Override
	public GetByCustomerResponse getById(int id) {
		Customer customer = this.customerRepository.findById(id);
		GetByCustomerResponse response = GetByCustomerResponse.builder()
				.id(customer.getId())
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.identity(customer.getIdentity())
				.birthYear(customer.getBirthYear())
				.build();
		return response;
	}

	@Override
	public List<GetAllCustomersResponse> getAll() {
		List<Customer> customers = this.customerRepository.findAllByOrderByIdAsc();
		List<GetAllCustomersResponse> response = customers.stream()
				.map(customer -> GetAllCustomersResponse.builder()
						.id(customer.getId())
						.firstName(customer.getFirstName())
						.lastName(customer.getLastName())
						.identity(customer.getIdentity())
						.birthYear(customer.getBirthYear())
						.build())
				.collect(Collectors.toList());
		return response;
	}
	
	@Override
	public Customer getByCustomerId(int id) {
		customerBusinessRules.checkIfCustomerIdExists(id);

		Customer customer = this.customerRepository.findById(id);
		return customer;
	}
}
