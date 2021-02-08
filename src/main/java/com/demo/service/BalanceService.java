package com.demo.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.demo.domain.Balance;
import com.demo.repository.BalanceRepository;

@Service
public class BalanceService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private BalanceRepository balanceRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Inserting Balance Object
	 * 
	 * @param balance
	 * @return
	 */
	public String save(Balance balance) {
		balance.setLastUpdatedTimeStamp(new Date());
		logger.info("Inserting Balance Object : " + balance);
		mongoTemplate.save(balance);
		return "inserted balance successfully";
	}

	/**
	 * Fetching Latest balance by an accountNumber
	 * 
	 * @param accountNumber
	 * @return balance
	 */
	public Balance getBalanceByAccountNumber(String accountNumber) {
		Balance balance = balanceRepository.findByAccountNumber(accountNumber);
		if (balance == null) {
			return null;
		}
		logger.info("Latest balance by an accountNumber " + accountNumber + " is :: " + balance.getBalance());
		return balance;
	}
}
