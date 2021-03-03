package com.anz.accounts.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.accounts.exception.RecordNotFoundException;
import com.anz.accounts.model.AccountDetailsEntity;
import com.anz.accounts.model.AccountResponse;
import com.anz.accounts.model.CustomerDetailsEntity;
import com.anz.accounts.model.TransactionDetailsEntity;
import com.anz.accounts.model.TransactionResponse;
import com.anz.accounts.repository.AccountDetailsRepository;
import com.anz.accounts.repository.CustomerDetailsRepository;
import com.anz.accounts.repository.TransactionDetailsRepository;
import com.anz.accounts.util.AccountsUtil;

/**
 * This is the Service class to retrieve data from repository.
 * 
 * @author Vasavi
 *
 */
@Service
public class AccountDetailsService implements IAccountDetailsService{

	private static Logger logger = LoggerFactory.getLogger(AccountDetailsService.class);

	@Autowired
	AccountDetailsRepository accountRepository;

	@Autowired
	CustomerDetailsRepository customerRepository;

	@Autowired
	TransactionDetailsRepository transactionRepository;

	/**
	 * This method will get all the accounts for the given customer. This will check
	 * whether customer exists or not and also checks whether customer holds any
	 * accounts in case of a valid customer and returns message accordingly.
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<AccountResponse> getAccounts(BigInteger customerId) throws RecordNotFoundException {
		List<AccountResponse> accounts=null;
		if (null != customerId) {

			Optional<CustomerDetailsEntity> customer = customerRepository.findById(customerId);
			if (customer.isPresent()) {
				accounts= getAccountsForCustomer(customer.get());
			}else {
				logger.debug("Customer does not exist.");
				throw new RecordNotFoundException("Customer does not exist.");
			}
		} else {
			logger.debug("Invalid customer Id.");
			throw new RecordNotFoundException("Invalid customer Id.");
		}
		return accounts;
	}

	/**
	 * This method will return all the transactions associated with the given
	 * account no. It also checks for the existence of account and transactions for
	 * that account.
	 * 
	 * @param accountNo
	 * @return
	 * @throws Exception
	 */
	public List<TransactionResponse> getTransactions(BigInteger accountNo) throws RecordNotFoundException {
		List<TransactionResponse> transactions=null;
		if (null != accountNo) {
			Optional<AccountDetailsEntity> account = accountRepository.findById(accountNo);

			if (account.isPresent()) {
				transactions=getTransactionsForAccount(account.get());
				
			} else {
				logger.debug("Account No. does not exist.");
				throw new RecordNotFoundException("Account No. does not exist.");
			}

		} else {
			logger.debug("Invalid Account No.");
			throw new RecordNotFoundException("Invalid Account No.");
		}
		return transactions;
	}
	
	/**
	 * This method will check if customer has any accounts or not, and return the processed accounts.
	 * @param customer
	 * @return
	 */
	private List<AccountResponse> getAccountsForCustomer(CustomerDetailsEntity customer){
		List<AccountDetailsEntity> accounts = customer.getAccounts();
		if (null!=accounts && accounts.size() > 0) {
			List<AccountResponse> accountList = accounts.stream().map(accountEntity -> {
				AccountResponse account = new AccountResponse();

				BeanUtils.copyProperties(accountEntity, account);

				return account;
			}).collect(Collectors.toList());
			logger.info("Customer has accounts, and returning the processed accounts.");
			return accountList;

		} else {
			// Send empty list as customer id is valid but does not have any accounts.
			logger.info("Customer does not have accounts.");
			return new ArrayList<AccountResponse>();

		}
	}
	
	private List<TransactionResponse> getTransactionsForAccount(AccountDetailsEntity account){
		List<TransactionDetailsEntity> trnasactions = account.getTransactions();
		if (null!=trnasactions && trnasactions.size() > 0) { //If transactions exist
			List<TransactionResponse> transactionList = trnasactions.stream().map(transactionEntity -> {
				TransactionResponse transaction = new TransactionResponse();

				BeanUtils.copyProperties(transactionEntity, transaction);
				BeanUtils.copyProperties( account,transaction);
				transaction.setTransactionDate(
						AccountsUtil.formatTransactionDate(transactionEntity.getTransactionDate()));

				return transaction;
			}).collect(Collectors.toList());
			logger.info("Returning processed transactions list for the given Account No.");
			return transactionList;

		} else {
			// Send empty list as the account no is valid but it does not have any
			// transactions.
			logger.info("Account no. is valid, but it does not have any transactions.");
			return new ArrayList<TransactionResponse>();
		}
	}
}
