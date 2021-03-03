package com.anz.accounts.service;

import java.math.BigInteger;
import java.util.List;

import com.anz.accounts.exception.RecordNotFoundException;
import com.anz.accounts.model.AccountResponse;
import com.anz.accounts.model.TransactionResponse;
/**
 *  Interface for Account details Service
 * @author Vasavi
 *
 */
public interface IAccountDetailsService {
	public List<AccountResponse> getAccounts(BigInteger customerId) throws RecordNotFoundException;
	public List<TransactionResponse> getTransactions(BigInteger accountNo) throws RecordNotFoundException ;

}
