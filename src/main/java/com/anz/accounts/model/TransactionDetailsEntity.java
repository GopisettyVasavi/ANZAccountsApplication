package com.anz.accounts.model;


import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Persistent Entity object for Phone Details.
 * 
 * @author Vasavi
 *
 */
@Entity
@Table(name = "TBL_TRANSACTION_DETAILS")
public class TransactionDetailsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	@Id
	@Column(name = "transaction_id")
	private BigInteger transactionId;
	
	@Column(name = "traction_type")
	private String transactionType;
	
	@Column(name = "credit_amount")
	private  Double creditAmount;
	
	@Column(name = "debit_amount")
	private  Double debitAmount;
	
	@Column(name = "transaction_narrative")
	private String transactionNarrative;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	

	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinColumn(name = "account_no")
	    private AccountDetailsEntity account;


	public BigInteger getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public Double getCreditAmount() {
		return creditAmount;
	}


	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}


	public Double getDebitAmount() {
		return debitAmount;
	}


	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}


	public String getTransactionNarrative() {
		return transactionNarrative;
	}


	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}


	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}


	public AccountDetailsEntity getAccount() {
		return account;
	}


	public void setAccount(AccountDetailsEntity account) {
		this.account = account;
	}

public TransactionDetailsEntity() {
	super();
}
	public TransactionDetailsEntity(BigInteger transactionId, String transactionType, Double creditAmount,
			Double debitAmount, String transactionNarrative, LocalDateTime transactionDate,
			AccountDetailsEntity account) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
		this.transactionNarrative = transactionNarrative;
		this.transactionDate = transactionDate;
		this.account = account;
	}


}