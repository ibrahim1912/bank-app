package com.tiger.bankapp.services.abstracts;

import java.util.List;

import com.tiger.bankapp.services.requests.accounts.CreateAccountMoneyRequest;
import com.tiger.bankapp.services.requests.accounts.CreateAccountRequest;
import com.tiger.bankapp.services.requests.accounts.CreateMoneyTransferRequest;
import com.tiger.bankapp.services.requests.accounts.DeleteAccountRequest;
import com.tiger.bankapp.services.requests.accounts.UpdateAccountRequest;
import com.tiger.bankapp.services.responses.accounts.GetAllAccountsResponse;
import com.tiger.bankapp.services.responses.accounts.GetByAccountResponse;

public interface AccountService {

	void createAccount(CreateAccountRequest createAccountRequest);
	void deleteAccount(DeleteAccountRequest deleteAccountRequest);
	void updateAccount(UpdateAccountRequest updateAccountRequest);
	
	GetByAccountResponse getById(int id);
	List<GetAllAccountsResponse> getAll();
	
	void withdrawMoney(CreateAccountMoneyRequest createAccountMoneyRequest);
	void addMoney(CreateAccountMoneyRequest createAccountMoneyRequest);
	void transferMoney(CreateMoneyTransferRequest createMoneyTransferRequest);
}
