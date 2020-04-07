package com.mybank.su40013388.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue
	private Long accNo;

	@Column(name = "ACCOUNT_TYPE", length = 50, nullable = false)
	public String acctype;

	@Column(name = "ACCOUNT_SUB_TYPE", length = 50, nullable = false)
	public String accSubtype;

	@Column(name = "ACCOUNT_BALANCE", length = 50, nullable = false)
	public Long accBal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Customer customer;

	public Account() {
		super();
	}

	public Long getAccNo() {
		return accNo;
	}

	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}

	public String getAcctype() {
		return acctype;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

	public String getAccSubtype() {
		return accSubtype;
	}

	public void setAccSubtype(String accSubtype) {
		this.accSubtype = accSubtype;
	}

	public Long getAccBal() {
		return accBal;
	}

	public void setAccBal(Long accBal) {
		this.accBal = accBal;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
