package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Balance;
import com.demo.service.BalanceService;

@RestController
@RequestMapping("/balance")
public class BalanceController {

	@Autowired
	private BalanceService balanceService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * @param accountNumber
	 * @return Latest Balnace for given account number
	 * 
	 *         Request URL : localhost:1525/balance?accountNumber=123456
	 */
	@GetMapping(value = "/getBalanceByAccountNumber")
	public Balance getBalanceByAccountNumber(@RequestParam String accountNumber) {
		logger.info("entering into getBalanceByAccountNumber method :: " + accountNumber);
		return balanceService.getBalanceByAccountNumber(accountNumber);
	}

}
