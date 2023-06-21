package com.tiger.bankapp.services.concretes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tiger.bankapp.entities.concretes.Account;
import com.tiger.bankapp.entities.concretes.Customer;
import com.tiger.bankapp.repositories.AccountRepository;
import com.tiger.bankapp.services.abstracts.AccountService;
import com.tiger.bankapp.services.abstracts.CustomerService;
import com.tiger.bankapp.services.requests.accounts.CreateAccountMoneyRequest;
import com.tiger.bankapp.services.requests.accounts.CreateAccountRequest;
import com.tiger.bankapp.services.requests.accounts.CreateMoneyTransferRequest;
import com.tiger.bankapp.services.requests.accounts.DeleteAccountRequest;
import com.tiger.bankapp.services.requests.accounts.UpdateAccountRequest;
import com.tiger.bankapp.services.responses.accounts.GetAllAccountsResponse;
import com.tiger.bankapp.services.responses.accounts.GetByAccountResponse;
import com.tiger.bankapp.services.rules.AccountBusinessRules;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountManager implements AccountService {

	private final AccountRepository accountRepository;
	private final CustomerService customerService;
	private final AccountBusinessRules accountBusinessRules;

	@Override
	public void createAccount(CreateAccountRequest createAccountRequest) {
		Customer customer = this.customerService.getByCustomerId(createAccountRequest.getCustomerId());
		Account account = Account.builder()
				.accountName(createAccountRequest.getAccountName())
				.accountNumber(generateRandomNumber())
				.balance(createAccountRequest.getBalance())
				.customer(customer)
				.build();

		this.accountRepository.save(account);

	}

	@Override
	public void deleteAccount(DeleteAccountRequest deleteAccountRequest) {
		this.accountBusinessRules.checkIfAccountIdExists(deleteAccountRequest.getId());
		
		this.accountRepository.deleteById(deleteAccountRequest.getId());

	}

	@Override
	public void updateAccount(UpdateAccountRequest updateAccountRequest) {
		this.accountBusinessRules.checkIfAccountIdExists(updateAccountRequest.getId());
		
		Account account = this.accountRepository.findById(updateAccountRequest.getId());
		Account acc = Account.builder()
				.id(updateAccountRequest.getId())
				.accountName(updateAccountRequest.getAccountName())
				.build();
		acc.setAccountNumber(account.getAccountNumber());
		acc.setBalance(account.getBalance());
		acc.setCustomer(account.getCustomer());
		
		
		this.accountRepository.save(acc);

	}

	@Override
	public GetByAccountResponse getById(int id) {
		this.accountBusinessRules.checkIfAccountIdExists(id);
		
		Account account = this.accountRepository.findById(id);
		GetByAccountResponse response = GetByAccountResponse.builder()
				.id(account.getId())
				.balance(account.getBalance())
				.accountName(account.getAccountName())
				.accountNumber(account.getAccountNumber())
				.customerId(account.getCustomer() == null ? null : account.getCustomer().getId())
				.customerIdentity(account.getCustomer() == null ? null : account.getCustomer().getIdentity())
				.build();
		return response;
	}

	@Override
	public List<GetAllAccountsResponse> getAll() {
		List<Account> accounts = this.accountRepository.findAllByOrderByIdAsc();

		List<GetAllAccountsResponse> response = accounts.stream()
				.map(account -> GetAllAccountsResponse.builder()
						.id(account.getId())
						.balance(account.getBalance())
						.accountName(account.getAccountName())
						.accountNumber(account.getAccountNumber())
						.customerId(account.getCustomer() == null ? null : account.getCustomer().getId())
						.customerIdentity(account.getCustomer() == null ? null : account.getCustomer().getIdentity())
						.build())
				.collect(Collectors.toList());

		return response;
	}

	

	@Override
	public void withdrawMoney(CreateAccountMoneyRequest createAccountMoneyRequest) {
		this.accountBusinessRules.checkIfAccountIdExists(createAccountMoneyRequest.getId());

		Account account = this.accountRepository.findById(createAccountMoneyRequest.getId());
		Double balance = account.getBalance();

		this.accountBusinessRules.checkIfBalanceGreaterThanAmount(balance, createAccountMoneyRequest.getAmount());

		account.setBalance(balance - createAccountMoneyRequest.getAmount());
		this.accountRepository.save(account);
		
	}

	@Override
	public void addMoney(CreateAccountMoneyRequest createAccountMoneyRequest) {
		this.accountBusinessRules.checkIfAccountIdExists(createAccountMoneyRequest.getId());

		Account account = this.accountRepository.findById(createAccountMoneyRequest.getId());
		Double balance = account.getBalance();
		Double amount = createAccountMoneyRequest.getAmount();

		this.accountBusinessRules.checkIfAmountGreaterThanZero(amount);

		account.setBalance(balance + amount);
		this.accountRepository.save(account);
		
	}
	
	@Transactional
	@Override
	public void transferMoney(CreateMoneyTransferRequest createMoneyTransferRequest) {
		this.accountBusinessRules.checkIfAccountIdExists(createMoneyTransferRequest.getFromId());
		this.accountBusinessRules.checkIfAccountIdExists(createMoneyTransferRequest.getToId());
		
		Account accountFrom = this.accountRepository.findById(createMoneyTransferRequest.getFromId());
		Account accountTo = this.accountRepository.findById(createMoneyTransferRequest.getToId());
		
		this.accountBusinessRules.checkIfTransferMoneyBalanceGreaterThanAmount(accountFrom.getBalance(),
				createMoneyTransferRequest.getAmount());
		
		
		accountFrom.setBalance(accountFrom.getBalance() - createMoneyTransferRequest.getAmount());
		accountTo.setBalance(accountTo.getBalance() + createMoneyTransferRequest.getAmount());
		
		saveAll(Arrays.asList(accountFrom,accountTo));
	}
	
	private String generateRandomNumber() {
		return String.valueOf(new Random().nextInt((9 * (int) Math.pow(10, 9))));
	}
	
	private void saveAll(List<Account> accounts){
		this.accountRepository.saveAll(accounts);
	}

	
}
