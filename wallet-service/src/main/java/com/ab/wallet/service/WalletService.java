/**
 * 
 */
package com.ab.wallet.service;

import com.ab.wallet.rest.domain.TransactionRequest;
import com.ab.wallet.rest.domain.TransactionResponse;

/**
 * @author Bovas
 *
 */
public interface WalletService {
	
	TransactionResponse performTransaction(TransactionRequest request);
}
