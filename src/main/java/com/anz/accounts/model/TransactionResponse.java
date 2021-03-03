package com.anz.accounts.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *  Transaction response object with all necessary response details
 * @author Vasavi
 *
 */
public class TransactionResponse implements Serializable{
	
	private static final long serialVersionUID = 5L;
	private BigInteger accountNo;
	private String accountName;
	private String transactionDate;
	private String currency;
	private Double debitAmount;
	private Double creditAmount;
	private String transactionType;
	private String transactionNarrative;
	public BigInteger getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(BigInteger accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionNarrative() {
		return transactionNarrative;
	}
	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}
	public TransactionResponse(BigInteger accountNo, String accountName,  String currency,
			Double debitAmount, Double creditAmount, String transactionType, String transactionNarrative) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.currency = currency;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.transactionType = transactionType;
		this.transactionNarrative = transactionNarrative;
	}
	public TransactionResponse() {
		super();
	}

	
	

}
