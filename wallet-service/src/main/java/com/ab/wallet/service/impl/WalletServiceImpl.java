/**
 * 
 */
package com.ab.wallet.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.wallet.config.Constants;
import com.ab.wallet.entities.PlayerAccount;
import com.ab.wallet.exception.PlayerNotFoundException;
import com.ab.wallet.repo.PlayerAccountRepository;
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
	
	@Override
	public TransactionResponse performTransaction(TransactionRequest request) throws Exception {
		TransactionResponse response = null;
		if(StringUtils.equals(request.getTransactionType(), Constants.CREDIT_TRANSACTION)) {
			response = makeCredit(request);
		}else if(StringUtils.equals(request.getTransactionType(), Constants.DEBIT_TRANSACTION)) {
			response = makeDebit(request);
		}else {
			throw new UnsupportedOperationException("Unsupported transaction");
		}
		return response;
	}

	private TransactionResponse makeDebit(TransactionRequest request) throws PlayerNotFoundException {
		TransactionResponse response = new TransactionResponse();
		PlayerAccount account = retrievePlayerAccount(request.getPlayerId());
		double currentBalance = account.getCurrentBalance() - request.getTransactionAmount();		
		if(currentBalance>=0) {			
			savePlayerAccount(account, currentBalance);
		}
		return response;
	}	

	private TransactionResponse makeCredit(TransactionRequest request) throws PlayerNotFoundException {
		TransactionResponse response = new TransactionResponse();
		PlayerAccount account = retrievePlayerAccount(request.getPlayerId());
		double currentBalance = account.getCurrentBalance() + request.getTransactionAmount();		
		savePlayerAccount(account, currentBalance);		
		return response;
	}
	
	private PlayerAccount retrievePlayerAccount(long playerId) throws PlayerNotFoundException {		
		Optional<PlayerAccount> playerOptional = playerRepository.findById(playerId);
		if(playerOptional.isPresent()) {
			return playerOptional.get();
		}
		throw new PlayerNotFoundException("Player not found for the id "+playerId) ;
	}
	
	private void savePlayerAccount(PlayerAccount account, double currentBalance) {
		account.setCurrentBalance(currentBalance);
	}
}
