package com.demo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Balance {
	@Id
	private String id;
	private String accountNumber;
	private Date lastUpdatedTimeStamp;
	private Double balance=0.0;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedTimeStamp == null) ? 0 : lastUpdatedTimeStamp.hashCode());
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
		Balance other = (Balance) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedTimeStamp == null) {
			if (other.lastUpdatedTimeStamp != null)
				return false;
		} else if (!lastUpdatedTimeStamp.equals(other.lastUpdatedTimeStamp))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Balance [id=" + id + ", accountNumber=" + accountNumber + ", lastUpdatedTimeStamp="
				+ lastUpdatedTimeStamp + ", balance=" + balance + "]";
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

	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}

	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
