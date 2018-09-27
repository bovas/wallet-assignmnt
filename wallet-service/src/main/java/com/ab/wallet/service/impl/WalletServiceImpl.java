/**
 * 
 */
package com.ab.wallet.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.wallet.config.Constants;
import com.ab.wallet.entities.PlayerAccount;
import com.ab.wallet.entities.TransactionLog;
import com.ab.wallet.exception.PlayerNotFoundException;
import com.ab.wallet.repo.PlayerAccountRepository;
import com.ab.wallet.repo.TransactionLogRepository;
import com.ab.wallet.rest.domain.TransactionRequest;
import com.ab.wallet.rest.domain.TransactionResponse;
import com.ab.wallet.service.WalletService;

/**
 * @author Bovas
 *
 */
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private PlayerAccountRepository playerRepository;
	
	@Autowired
	private TransactionLogRepository transactionLogRepository;
	
	@Override
	public TransactionResponse performTransaction(TransactionRequest request) throws Exception {		
		if(isDuplicationTransaction(request)) {
			return retrieveDuplicateTxResponse(request);
		}
		
		TransactionResponse response = null;
		if(StringUtils.equalsIgnoreCase(request.getTransactionType(), Constants.CREDIT_TRANSACTION)) {
			response = makeCredit(request);
		}else if(StringUtils.equalsIgnoreCase(request.getTransactionType(), Constants.DEBIT_TRANSACTION)) {
			response = makeDebit(request);
		}else {
			throw new UnsupportedOperationException("Unsupported transaction");
		}
		return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws PlayerNotFoundException
	 */
	protected TransactionResponse makeDebit(TransactionRequest request) throws PlayerNotFoundException {		
		PlayerAccount account = retrievePlayerAccount(request.getPlayerId());
		double currentBalance = account.getCurrentBalance() - request.getTransactionAmount();
		UUID transactionId = request.getTransactionId();
		boolean transactionStatus = false;
		String statusReason = null;
		if(currentBalance>=0) {			
			transactionStatus = savePlayerAccount(account, currentBalance);
			if(transactionStatus) {
				saveTransactionLog(request, account);
			}
		}else {
			statusReason = "Insufficient account balance.";
		}
		TransactionResponse response = retrieveTransactionResponse(account, transactionStatus, transactionId);
		response.setTransactionStatusReason(statusReason);
		return response;
	}		

	/**
	 * @param request
	 * @return
	 * @throws PlayerNotFoundException
	 */
	protected TransactionResponse makeCredit(TransactionRequest request) throws PlayerNotFoundException {		
		PlayerAccount account = retrievePlayerAccount(request.getPlayerId());
		double currentBalance = account.getCurrentBalance() + request.getTransactionAmount();		
		UUID transactionId = request.getTransactionId();
		boolean transactionStatus = savePlayerAccount(account, currentBalance);
		if(transactionStatus) {
			saveTransactionLog(request, account);
		}
		return retrieveTransactionResponse(account, transactionStatus, transactionId);		
	}		

	private boolean isDuplicationTransaction(TransactionRequest request) {		
		return retrieveTransactionLog(request.getTransactionId())!=null;
	}
	
	private TransactionResponse retrieveDuplicateTxResponse(TransactionRequest request) {
		TransactionResponse response = retrieveTransactionResponse(null, false, request.getTransactionId());
		response.setTransactionStatusReason("Duplicate Transaction.");
		return response;		
	}

	private TransactionResponse retrieveTransactionResponse(PlayerAccount account, boolean transactionStatus, UUID transactionId) {
		TransactionResponse response = new TransactionResponse();
		response.setTransactionId(transactionId);
		if(transactionStatus) {
			response.setCurrentBalance(account.getCurrentBalance());			
			response.setTransactionStatusMessage("Transaction is successful.");
		}else{
			response.setTransactionStatusMessage("Transaction failed");
		}
		return response;
	}
	
	/**
	 * @param playerId
	 * @return
	 * @throws PlayerNotFoundException
	 */
	private PlayerAccount retrievePlayerAccount(long playerId) throws PlayerNotFoundException {		
		Optional<PlayerAccount> playerOptional = playerRepository.findById(playerId);
		if(playerOptional.isPresent()) {
			return playerOptional.get();
		}
		throw new PlayerNotFoundException("Player not found for the id "+playerId) ;
	}
	
	/**
	 * @param account
	 * @param currentBalance
	 * @return
	 * @throws PlayerNotFoundException
	 */
	private boolean savePlayerAccount(PlayerAccount account, double currentBalance) throws PlayerNotFoundException {
		account.setCurrentBalance(currentBalance);
		playerRepository.save(account);
		return retrievePlayerAccount(account.getPlayerId()).getCurrentBalance() == currentBalance;
	}
	
	/**
	 * @param transactionId
	 * @return
	 */
	private TransactionLog retrieveTransactionLog(UUID transactionId) {
		Optional<TransactionLog> transactionLogOptional = transactionLogRepository.findByTransactionId(transactionId);
		return transactionLogOptional.isPresent() ? transactionLogOptional.get() : null;
	}
	
	/**
	 * @param request
	 * @param account
	 */
	private void saveTransactionLog(TransactionRequest request, PlayerAccount account) {
		TransactionLog transactionLog = new TransactionLog();
		transactionLog.setPlayerId(account.getPlayerId());
		transactionLog.setTransactionId(request.getTransactionId());
		transactionLog.setTransactionStatus("Completed");
		transactionLogRepository.save(transactionLog);
	}
}
