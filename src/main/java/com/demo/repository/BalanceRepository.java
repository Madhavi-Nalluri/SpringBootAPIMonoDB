package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.domain.Balance;

@Repository
public interface BalanceRepository extends MongoRepository<Balance, String> {

	List<Balance> findAll();

	Optional<Balance> findById(String id);

	@Query(value = "{accountNumber : ?0}")
	Balance findByAccountNumber(String accountNumber);

	Balance findByBalance(Double balance);
}
