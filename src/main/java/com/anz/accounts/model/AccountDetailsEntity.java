package com.anz.accounts.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Persistent Entity object for Phone Details.
 * 
 * @author Vasavi
 *
 */
@Entity
@Table(name = "TBL_ACCOUNT_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "transactions" })
public class AccountDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_no")
	private BigInteger accountNo;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "currency")
	private String currency;

	@Column(name = "account_balance")
	private Double accountBalance;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	private CustomerDetailsEntity customer;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<TransactionDetailsEntity> transactions = new ArrayList<TransactionDetailsEntity>();

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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public CustomerDetailsEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetailsEntity customer) {
		this.customer = customer;
	}

	public List<TransactionDetailsEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDetailsEntity> transactions) {
		this.transactions = transactions;
	}

	public AccountDetailsEntity() {
		super();
	}

	public AccountDetailsEntity(BigInteger accountNo) {
		super();
		this.accountNo = accountNo;

	}
	public AccountDetailsEntity(BigInteger accountNo, List<TransactionDetailsEntity> transactions) {
		super();
		this.accountNo = accountNo;
		this.transactions=transactions;

	}

	public AccountDetailsEntity(BigInteger accountNo, String accountName, String accountType, String currency,
			Double accountBalance) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.accountType = accountType;
		this.currency = currency;
		this.accountBalance = accountBalance;

	}

}