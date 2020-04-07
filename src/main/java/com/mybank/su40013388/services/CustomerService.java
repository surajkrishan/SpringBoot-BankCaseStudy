package com.mybank.su40013388.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mybank.su40013388.entities.Customer;
import com.mybank.su40013388.exceptions.CustomerExistsException;
import com.mybank.su40013388.exceptions.CustomerNotFoundException;
import com.mybank.su40013388.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	// getAllUsers Method
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();

	}

	public Customer createCustomer(Customer customer) throws CustomerExistsException {
		Customer existingCustomer = customerRepository.findByCid(customer.getCid());

		if (existingCustomer != null) {
			throw new CustomerExistsException("Customer already exists in DB");
		}

		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerById(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer Not found in DB");
		}
		return customer;
	}

	public Customer updateCustomerById(Long id, Customer customer) throws CustomerNotFoundException {
		Optional<Customer> optionalcustomer = customerRepository.findById(id);

		if (!optionalcustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer Not found in DB, provide the correct id");
		}

		customer.setId(id);
		return customerRepository.save(customer);

	}

	// deleteUserById
	public void deleteCustomerById(Long id) {
		Optional<Customer> optionalcustomer = customerRepository.findById(id);
		if (!optionalcustomer.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Customer Not found in DB, provide the correct id");
		}

		customerRepository.deleteById(id);
	}

	public Customer getCustomerById(String cid) {
		return customerRepository.findByCid(cid);
	}
}
