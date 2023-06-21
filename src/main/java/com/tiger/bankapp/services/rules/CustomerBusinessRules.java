package com.tiger.bankapp.services.rules;

import org.springframework.stereotype.Service;

import com.tiger.bankapp.common.utilities.exceptions.BusinessException;
import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {

	private final CustomerRepository customerRepository;
	
	public void checkIfCustomerExists(String identity) {
		Customer customer = customerRepository.findByIdentity(identity);
		if (customer != null)
			throw new BusinessException("Müşteri mevcut!");

	}
	
	public void checkIfCustomerIdExists(int id) {
		Customer customer= this.customerRepository.findById(id);
		if (customer == null) {
			throw new BusinessException("Böyle bir müşteri bulunamadı");
		}
	}
	
	public void checkIfIdentityIsSameForUpdate(int id, String identity) {
		Customer customer= this.customerRepository.findById(id);
		if (!customer.getIdentity().equals(identity)) {
			checkIfCustomerExists(identity);
		}
	}

	
}
