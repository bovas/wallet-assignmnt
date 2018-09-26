/**
 * 
 */
package com.ab.wallet.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.wallet.rest.domain.TransactionRequest;
import com.ab.wallet.rest.domain.TransactionResponse;

/**
 * @author Bovas
 *
 */
@RestController
@RequestMapping("wallet")
public class WalletServiceRestController {

	@PostMapping("/transaction/add")
	public TransactionResponse performTransaction(@RequestBody TransactionRequest request) {
		return null;
	}
}
