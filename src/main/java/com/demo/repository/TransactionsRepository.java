package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.domain.Transactions;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, String> {

	List<Transactions> findAll();

	Optional<Transactions> findById(String id);

	@Query(value = "{accountNumber : ?0}")
	List<Transactions> findByAccountNumber(String accountNumber);
}
