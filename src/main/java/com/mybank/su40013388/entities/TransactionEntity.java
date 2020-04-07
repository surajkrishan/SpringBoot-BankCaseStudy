package com.mybank.su40013388.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "FROM_ACCOUNT", length = 50, nullable = true)
	public Long fromAcc;

	@Column(name = "TO_ACCOUNT", length = 50, nullable = true)
	public Long toAcc;

	@Column(name = "TXN_AMOUNT", length = 50, nullable = true)
	public Long amount;

	public TransactionEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(Long fromAcc) {
		this.fromAcc = fromAcc;
	}

	public Long getToAcc() {
		return toAcc;
	}

	public void setToAcc(Long toAcc) {
		this.toAcc = toAcc;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
