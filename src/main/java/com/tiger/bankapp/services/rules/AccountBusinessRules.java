package com.tiger.bankapp.services.rules;

import org.springframework.stereotype.Service;

import com.tiger.bankapp.common.utilities.exceptions.BusinessException;
import com.tiger.bankapp.entities.concretes.Account;
import com.tiger.bankapp.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountBusinessRules {

	private final AccountRepository accountRepository;
	
	public void checkIfAccountIdExists(int id) {
		Account account = this.accountRepository.findById(id);
		if (account == null) {
			throw new BusinessException("Böyle bir hesap yok");
		}
	}
	
	public void checkIfBalanceGreaterThanAmount(Double balance,Double amount) {
		if(balance<amount) {
			throw new BusinessException("Para çekilemez");
		}
	}
	
	public void checkIfAmountGreaterThanZero(Double amount) {
		if (amount < 0) {
			throw new BusinessException("Eksi bakiye eklenemez");
		}
	}
	
	public void checkIfTransferMoneyBalanceGreaterThanAmount(Double balance,Double amount) {
		if (balance < amount) {
			throw new BusinessException("Para transfer edilemez bakiye yetersiz");
		}
	}
}
