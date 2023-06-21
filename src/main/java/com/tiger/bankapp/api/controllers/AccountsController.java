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

import com.tiger.bankapp.entities.concretes.Account;
import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.services.abstracts.AccountService;
import com.tiger.bankapp.services.requests.accounts.CreateAccountMoneyRequest;
import com.tiger.bankapp.services.requests.accounts.CreateAccountRequest;
import com.tiger.bankapp.services.requests.accounts.CreateMoneyTransferRequest;
import com.tiger.bankapp.services.requests.accounts.DeleteAccountRequest;
import com.tiger.bankapp.services.requests.accounts.UpdateAccountRequest;
import com.tiger.bankapp.services.responses.accounts.GetAllAccountsResponse;
import com.tiger.bankapp.services.responses.accounts.GetByAccountResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountsController {

	private final AccountService accountService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<GetAllAccountsResponse>> getAll() {
		List<GetAllAccountsResponse> accounts = accountService.getAll();
		return ResponseEntity.ok(accounts);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<GetByAccountResponse> getById(@PathVariable int id) {
		GetByAccountResponse response = accountService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
		this.accountService.createAccount(createAccountRequest);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest) {
		this.accountService.updateAccount(updateAccountRequest);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Account> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) {
		this.accountService.deleteAccount(deleteAccountRequest);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("withdrawmoney")
	public ResponseEntity<Account> withDrawMmoney(@RequestBody CreateAccountMoneyRequest createAccountMoneyRequest) {
		this.accountService.withdrawMoney(createAccountMoneyRequest);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("addmoney")
	public ResponseEntity<Account> addMoney(@RequestBody CreateAccountMoneyRequest createAccountMoneyRequest) {
		this.accountService.addMoney(createAccountMoneyRequest);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("transfermoney")
	public ResponseEntity<Account> transferMoney(@RequestBody CreateMoneyTransferRequest createMoneyTransferRequest) {
		this.accountService.transferMoney(createMoneyTransferRequest);
		return ResponseEntity.ok().build();
	}
}
