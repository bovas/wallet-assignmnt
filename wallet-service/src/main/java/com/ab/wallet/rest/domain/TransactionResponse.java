/**
 * 
 */
package com.ab.wallet.rest.domain;

import java.util.UUID;

/**
 * @author Bovas
 *
 */
public class TransactionResponse {
	
	private UUID transactionId;
	private Double currentBalance;
	private String transactionStatusMessage;
	private String transactionStatusReason;
	
	/**
	 * @return the transactionId
	 */
	public UUID getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the currentBalance
	 */
	public Double getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * @return the transactionStatusMessage
	 */
	public String getTransactionStatusMessage() {
		return transactionStatusMessage;
	}
	/**
	 * @param transactionStatusMessage the transactionStatusMessage to set
	 */
	public void setTransactionStatusMessage(String transactionStatusMessage) {
		this.transactionStatusMessage = transactionStatusMessage;
	}
	/**
	 * @return the transactionStatusReason
	 */
	public String getTransactionStatusReason() {
		return transactionStatusReason;
	}
	/**
	 * @param transactionStatusReason the transactionStatusReason to set
	 */
	public void setTransactionStatusReason(String transactionStatusReason) {
		this.transactionStatusReason = transactionStatusReason;
	}
	
	
}
