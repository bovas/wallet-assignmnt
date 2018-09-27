package com.ab.wallet.rest.domain;

import java.util.UUID;

import org.springframework.lang.NonNull;

/**
 * @author Bovas
 *
 */
public class TransactionRequest {

	@NonNull
	private UUID transactionId;
	
	@NonNull
	private String transactionType;
	
	@NonNull
	private Double transactionAmount;

	@NonNull
	private long playerId;
	
	/**
	 * @return the playerId
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

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
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionAmount
	 */
	public Double getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
