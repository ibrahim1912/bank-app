package com.tiger.bankapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiger.bankapp.entities.concretes.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findById(int id);
	List<Account> findAllByOrderByIdAsc();
}
