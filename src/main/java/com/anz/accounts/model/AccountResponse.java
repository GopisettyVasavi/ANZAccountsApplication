package com.anz.accounts.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.anz.accounts.util.AccountsUtil;
/**
 *  Response object to hold account data
 * @author Vasavi
 *
 */
public class AccountResponse  implements Serializable{

	
	private static final long serialVersionUID = 4L;

	private BigInteger accountNo;

	private String accountName;

	private String accountType;
	
	private String balanceDate;

	private String currency;

	private Double accountBalance;

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

	public String getBalanceDate() {
		balanceDate=AccountsUtil.formatDate(LocalDate.now());
		return  balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate =balanceDate;
	}

	public AccountResponse(BigInteger accountNo) {
		super();
		this.accountNo = accountNo;
	}

	public AccountResponse() {
		super();
	}

	
}
