package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Transactions;
import com.demo.domain.Transactions.Type;
import com.demo.dto.ResultListDto;
import com.demo.service.TransactionsService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	private TransactionsService transactionsService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * Request URL : http://localhost:1525/transactions/
	 * 
	 * @param transactions
	 * @return
	 * 
	 *         Request Body :
	 *         {"accountNumber":"123456","type":"DEPOSIT",amount":"10000"} OR
	 *         {"accountNumber":"123456","type":"WITHDRAW",amount":"10000"}
	 * 
	 */
	@PostMapping(value = "/")
	public String create(@RequestBody Transactions transactions) {
		logger.info("Entering into create method ::" + transactions);
		return transactionsService.save(transactions);
	}

	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param accountNumber
	 * @param start
	 * @param end
	 * @return Transactions
	 * @throws Exception
	 * 
	 *                   Request URL :
	 *                   localhost:http://1525/transactions/getTransactionsWithDateRange/2/5?accountNumber=123456&start=12-01-2020&end=02-28-2021
	 */
	@GetMapping(value = "/getTransactionsWithDateRange/{page}/{pageSize}")
	public ResultListDto<Transactions> getTransactionsWithDateRange(@PathVariable("page") int page,
			@PathVariable("pageSize") int pageSize, @RequestParam String accountNumber, @RequestParam String start,
			@RequestParam String end) throws Exception {
		logger.info("Entering into getTransactionsWithDateRange method");
		return transactionsService.getTransactionsWithDateRange(page, pageSize, accountNumber, start, end);
	}

	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param accountNumber
	 * @param type
	 * @param start
	 * @param end
	 * @return Transactions
	 * @throws Exception
	 * 
	 *                   Request URL :
	 *                   localhost:http://1525/transactions/getTransactionsWithDateRangeAndType/2/5?accountNumber=123456&type=DEPOSIT&start=12-01-2020&end=02-28-2021
	 */
	@GetMapping(value = "/getTransactionsWithDateRangeAndType/{page}/{pageSize}")
	public ResultListDto<Transactions> getTransactionsWithDateRangeAndType(@PathVariable("page") int page,
			@PathVariable("pageSize") int pageSize, @RequestParam String accountNumber, @RequestParam Type type,
			@RequestParam String start, @RequestParam String end) throws Exception {
		logger.info("Entering into getTransactionsWithDateRange method");
		return transactionsService.getTransactionsWithDateRangeAndType(page, pageSize, accountNumber, type, start, end);
	}

}
