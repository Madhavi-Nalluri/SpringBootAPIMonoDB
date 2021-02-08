package com.demo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.demo.constants.DemoConstants;
import com.demo.domain.Balance;
import com.demo.domain.Transactions;
import com.demo.domain.Transactions.Type;
import com.demo.dto.ResultListDto;
import com.demo.util.CommonUtility;

@Service
public class TransactionsService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private BalanceService balanceService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Inserting Transactions
	 * 
	 * @param transactions
	 * @return
	 */
	public String save(Transactions transactions) {

		Balance balance = balanceService.getBalanceByAccountNumber(transactions.getAccountNumber());
		if (balance == null) {
			balance = new Balance();
		}

		if (Type.DEPOSIT.equals(transactions.getType())) {
			logger.info("Depositing amount for an account " + balance.getAccountNumber());
			balance.setBalance(balance.getBalance() + transactions.getAmount());
		} else if (Type.WITHDRAW.equals(transactions.getType()) && balance.getBalance() > 0.0
				&& balance.getBalance() >= transactions.getAmount()) {
			logger.info("WithDrawing amount for an account " + balance.getAccountNumber());
			balance.setBalance(balance.getBalance() - transactions.getAmount());
		} else if (Type.WITHDRAW.equals(transactions.getType())
				&& (balance.getBalance() <= 0.0 || balance.getBalance() < transactions.getAmount())) {
			logger.info("Insufficient amount in your account" + balance.getAccountNumber());
			return "Insufficient amount in your account";
		}

		transactions.setTransationTs(new Date());
		logger.info("Inserting Transactions Object :" + transactions);
		mongoTemplate.save(transactions);

		balance.setAccountNumber(transactions.getAccountNumber());
		balanceService.save(balance);
		return "inserted transactions successfully";
	}

	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param accountNumber
	 * @param start
	 * @param end
	 * @return Transactions for a date range
	 * @throws Exception
	 */
	public ResultListDto<Transactions> getTransactionsWithDateRange(int page, int pageSize, String accountNumber,
			String start, String end) throws Exception {

		logger.info("Fetching Transactions with date range " + start + " and " + end);
		Query query = new Query();
		query = getQueryCrieria(query, accountNumber, start, end);
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, DemoConstants.TRANSATION_TS);
		query.with(pageable);
		final List<Transactions> transactions = mongoTemplate.find(query, Transactions.class);

		Page<Transactions> pageTransactionsList = new PageImpl<Transactions>(transactions, pageable, getCount(query));

		return setResultList(pageTransactionsList, page);
	}

	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param accountNumber
	 * @param type
	 * @param start
	 * @param end
	 * @return Transactions for a date range with type
	 * @throws Exception
	 */
	public ResultListDto<Transactions> getTransactionsWithDateRangeAndType(int page, int pageSize, String accountNumber,
			Type type, String start, String end) throws Exception {

		logger.info("Fetching Transactions with date range " + start + " and " + end + " and Type " + type);
		Query query = new Query();
		query.addCriteria(Criteria.where(DemoConstants.TYPE).is(type));
		query = getQueryCrieria(query, accountNumber, start, end);
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, DemoConstants.TRANSATION_TS);
		query.with(pageable);
		final List<Transactions> transactions = mongoTemplate.find(query, Transactions.class);

		Page<Transactions> pageTransactionsList = new PageImpl<Transactions>(transactions, pageable, getCount(query));

		return setResultList(pageTransactionsList, page);
	}

	/**
	 * 
	 * @param <T>
	 * @param pageTransactionsList
	 * @param page
	 * @return resultDto
	 */
	private <T> ResultListDto<T> setResultList(Page<T> pageTransactionsList, int page) {
		ResultListDto<T> resultDto = new ResultListDto<T>();
		resultDto.setResultList(pageTransactionsList.getContent());
		resultDto.setPageCount(pageTransactionsList.getTotalPages());
		resultDto.setTotalCount(pageTransactionsList.getTotalElements());
		resultDto.setCurrentPage(page);
		return resultDto;
	}

	/**
	 * 
	 * @param query
	 * @param accountNumber
	 * @param start
	 * @param end
	 * @return query
	 * @throws Exception
	 */
	private Query getQueryCrieria(Query query, String accountNumber, String start, String end) throws Exception {

		Date startDate = CommonUtility.getStartDate(start);
		Date endDate = CommonUtility.getEndDate(end);
		query.addCriteria(Criteria.where(DemoConstants.ACCOUNT_NUMBER).is(accountNumber));
		query.addCriteria(Criteria.where(DemoConstants.TRANSATION_TS).gte(startDate).lte(endDate));
		return query;
	}

	/**
	 * 
	 * @param query
	 * @return count of Transactions entities from the persistence store.
	 */
	public Long getCount(Query query) {
		return mongoTemplate.count(query, Transactions.class);
	}

}
