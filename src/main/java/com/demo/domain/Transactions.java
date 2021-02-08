package com.demo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Transactions {
	@Id
	private String id;
	private String accountNumber;
	private Date transationTs;
	private Double amount;
	private Type type;

	public enum Type {
		DEPOSIT, WITHDRAW;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((transationTs == null) ? 0 : transationTs.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (transationTs == null) {
			if (other.transationTs != null)
				return false;
		} else if (!transationTs.equals(other.transationTs))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", accountNumber=" + accountNumber + ", transationTs=" + transationTs
				+ ", amount=" + amount + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getTransationTs() {
		return transationTs;
	}

	public void setTransationTs(Date transationTs) {
		this.transationTs = transationTs;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
