package com.tiger.bankapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiger.bankapp.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int id);
	Customer findByIdentity(String identity);
	
	List<Customer> findAllByOrderByIdAsc();
}
