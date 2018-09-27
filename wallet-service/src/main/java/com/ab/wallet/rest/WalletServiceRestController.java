/**
 * 
 */
package com.ab.wallet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.wallet.rest.domain.TransactionRequest;
import com.ab.wallet.rest.domain.TransactionResponse;
import com.ab.wallet.service.WalletService;

/**
 * @author Bovas
 *
 */
@RestController
@RequestMapping("wallet")
public class WalletServiceRestController {

	@Autowired
	private WalletService walletService;
	
	@PostMapping("/transaction/add")
	public TransactionResponse performTransaction(@RequestBody TransactionRequest request) throws Exception {
		return walletService.performTransaction(request);
	}
}
