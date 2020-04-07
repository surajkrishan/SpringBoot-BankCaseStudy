package com.mybank.su40013388.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.su40013388.entities.Account;
import com.mybank.su40013388.entities.Customer;
import com.mybank.su40013388.entities.TransactionEntity;
import com.mybank.su40013388.exceptions.CustomerExistsException;
import com.mybank.su40013388.exceptions.CustomerNotFoundException;
import com.mybank.su40013388.services.AccountService;
import com.mybank.su40013388.services.CustomerService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/{id}")
	public List<Account> getAccount(@PathVariable Long id) throws CustomerNotFoundException {

		Optional<Customer> CustomerOptional = customerService.getCustomerById(id);
		if (!CustomerOptional.isPresent())
			throw new CustomerNotFoundException("Customer Not Found");

		return CustomerOptional.get().getAccount();
	}

	@PostMapping("/{id}")
	public Account createaccount(@PathVariable Long id, @RequestBody Account account)
			throws CustomerNotFoundException, CustomerExistsException {
		Optional<Customer> customerOptional = customerService.getCustomerById(id);

		if (!customerOptional.isPresent())
			throw new CustomerNotFoundException("User Not Found");

		List<Account> accObj = customerOptional.get().getAccount();
		if (accObj.isEmpty()) {
			Customer customer = customerOptional.get();
			account.setCustomer(customer);
			return accountService.createAccount(account);
		} else {
			throw new CustomerExistsException("Customer alredy had a account");
		}
	}

	@PutMapping("/sendMoney")
	public TransactionEntity sendMoney(@RequestBody TransactionEntity reqbody) {
		return accountService.addMoney(reqbody);
	}
}
