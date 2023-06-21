package com.tiger.bankapp.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.services.abstracts.CustomerService;
import com.tiger.bankapp.services.requests.customers.CreateCustomerRequest;
import com.tiger.bankapp.services.requests.customers.DeleteCustomerRequest;
import com.tiger.bankapp.services.requests.customers.UpdateCustomerRequest;
import com.tiger.bankapp.services.responses.customers.GetAllCustomersResponse;
import com.tiger.bankapp.services.responses.customers.GetByCustomerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {

	private final CustomerService customerService;

	@GetMapping("/getall")
	public ResponseEntity<List<GetAllCustomersResponse>> getAll() {
		List<GetAllCustomersResponse> customers = customerService.getAll();
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<GetByCustomerResponse> getById(@PathVariable int id) {
		GetByCustomerResponse response = this.customerService.getById(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		this.customerService.createCustomer(createCustomerRequest);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		this.customerService.updateCustomer(updateCustomerRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Customer> deleteCustomer(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		this.customerService.deleteCustomer(deleteCustomerRequest);
		return ResponseEntity.noContent().build();
	}
	
	
}
