package com.mybank.su40013388.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.su40013388.entities.Account;
import com.mybank.su40013388.entities.TransactionEntity;
import com.mybank.su40013388.repositories.AccountRepository;
import com.mybank.su40013388.repositories.TransactionRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepo;

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public TransactionEntity addMoney(TransactionEntity reqbody) {
		accountRepository.addAmount(reqbody.getAmount(), reqbody.getToAcc());
		accountRepository.deductAmount(reqbody.getAmount(), reqbody.getFromAcc());
		return transactionRepo.save(reqbody);
	}
}
