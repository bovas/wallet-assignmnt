/**
 * 
 */
package com.ab.wallet.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ab.wallet.entities.TransactionLog;

/**
 * @author Bovas
 *
 */
@Repository
public interface TransactionLogRepository extends CrudRepository<TransactionLog, Long>{
	
	Optional<TransactionLog> findByTransactionId(UUID transactionId);
	
}
