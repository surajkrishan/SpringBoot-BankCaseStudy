package com.mybank.su40013388.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.mybank.su40013388.entities.Customer;
import com.mybank.su40013388.exceptions.CidNotFoundException;
import com.mybank.su40013388.exceptions.CustomerExistsException;
import com.mybank.su40013388.exceptions.CustomerNotFoundException;
import com.mybank.su40013388.services.CustomerService;

@CrossOrigin
@RestController
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// getAllUsers Method
	@GetMapping("/customers")
	public List<Customer> getAllUsers() {

		return customerService.getAllCustomers();

	}

	@PostMapping("/customer")
	public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer, UriComponentsBuilder builder) {
		try {
			customerService.createCustomer(customer);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (CustomerExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable("id") Long id) {
		try {
			return customerService.getCustomerById(id);
		} catch (CustomerNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomerById(@PathVariable("id") Long id, @RequestBody Customer customer) {
		try {
			return customerService.updateCustomerById(id, customer);
		} catch (CustomerNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomerById(@PathVariable("id") Long id) {
		customerService.deleteCustomerById(id);
	}

	@GetMapping("/customerById/{cid}")
	public Customer getCustomerById(@PathVariable("cid") String cid) throws CidNotFoundException {
		Customer customer = customerService.getCustomerById(cid);
		if (customer == null)
			throw new CidNotFoundException("CID: '" + cid + "' not found DB");
		return customer;
	}
}