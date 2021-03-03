package com.anz.accounts.service;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.anz.accounts.exception.RecordNotFoundException;
import com.anz.accounts.model.AccountDetailsEntity;
import com.anz.accounts.model.AccountResponse;
import com.anz.accounts.model.CustomerDetailsEntity;
import com.anz.accounts.model.TransactionDetailsEntity;
import com.anz.accounts.model.TransactionResponse;
import com.anz.accounts.repository.AccountDetailsRepository;
import com.anz.accounts.repository.CustomerDetailsRepository;
import com.anz.accounts.service.AccountDetailsService;
import com.anz.accounts.service.IAccountDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsServiceTests {

	@InjectMocks
	IAccountDetailsService accountsService= new AccountDetailsService();

	@Mock
	CustomerDetailsRepository customerRepository;

	@Mock
	AccountDetailsRepository accountRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * This method will test if valid customer Id is given then it should return
	 * accounts associated with it.
	 */

	@Test
	public void givenValidCustomerIdThenReturnAccounts() {
		List<AccountDetailsEntity> accounts = populateAccounts();

		Mockito.when(customerRepository.findById(new BigInteger("55555"))).thenReturn(Optional
				.of(new CustomerDetailsEntity(new BigInteger("55555"), "Test Customer1", "Melbourne", accounts)));

		List<AccountResponse> accountsList = accountsService.getAccounts(new BigInteger("55555"));
		assertEquals(2, accountsList.size());

	}

	/**
	 * This method will test when a valid customer id is given but there are no
	 * accounts associated, then the returned list size is 0.
	 */
	@Test
	public void givenValidCustomerIdAndNoAccounts() {
		List<AccountDetailsEntity> accounts = new ArrayList<AccountDetailsEntity>();

		Mockito.when(customerRepository.findById(new BigInteger("55555"))).thenReturn(Optional
				.of(new CustomerDetailsEntity(new BigInteger("55555"), "Test Customer1", "Melbourne", accounts)));

		List<AccountResponse> accountsList = accountsService.getAccounts(new BigInteger("55555"));
		assertEquals(0, accountsList.size());

	}

	/**
	 * This method will test when given account no which does not exist, it should
	 * throw RecordNotFoundException.
	 */
	@Test(expected = RecordNotFoundException.class)
	public void givenInValidCustomerIdThenReturnNoAccounts() {

		Mockito.when(customerRepository.findById(new BigInteger("00000"))).thenThrow(RecordNotFoundException.class);

		accountsService.getAccounts(new BigInteger("00000"));

	}

	/**
	 * This method will test if given valid account no then transactions should be returned.
	 */
	@Test
	public void givenValidAccountNoThenReturnTransactions() {
		List<TransactionDetailsEntity> transactions = populateTransactions();

		Mockito.when(accountRepository.findById(new BigInteger("5544337788")))
				.thenReturn(Optional.of(new AccountDetailsEntity(new BigInteger("5544337788"), transactions)));

		List<TransactionResponse> transactionList = accountsService.getTransactions(new BigInteger("5544337788"));
		assertEquals(2, transactionList.size());

	}

	@Test
	public void givenInValidAccountNoThenReturnTransactions() {
		List<TransactionDetailsEntity> transactions = new ArrayList<TransactionDetailsEntity>();

		Mockito.when(accountRepository.findById(new BigInteger("777777")))
				.thenReturn(Optional.of(new AccountDetailsEntity(new BigInteger("777777"), transactions)));

		List<TransactionResponse> transactionList = accountsService.getTransactions(new BigInteger("777777"));
		assertEquals(0, transactionList.size());

	}

	/**
	 * This method will test when given account no which does not exist, it should
	 * throw RecordNotFoundException.
	 */
	@Test(expected = RecordNotFoundException.class)
	public void givenInValidAccountNoThenReturnNoTransactions() {

		Mockito.when(accountRepository.findById(new BigInteger("111111"))).thenThrow(RecordNotFoundException.class);

		accountsService.getTransactions(new BigInteger("111111"));

	}

	/**
	 * This method will create Account details objects and return the list.
	 * 
	 * @return
	 */
	private List<AccountDetailsEntity> populateAccounts() {
		List<AccountDetailsEntity> accounts = new ArrayList<AccountDetailsEntity>();

		AccountDetailsEntity accountOne = new AccountDetailsEntity(new BigInteger("5544337788"));
		AccountDetailsEntity accountTwo = new AccountDetailsEntity(new BigInteger("5544337799"));
		accounts.add(accountOne);
		accounts.add(accountTwo);

		return accounts;
	}

	/**
	 * This method will populate transactions list and return.
	 * 
	 * @return
	 */
	private List<TransactionDetailsEntity> populateTransactions() {
		List<TransactionDetailsEntity> transactions = new ArrayList<TransactionDetailsEntity>();
		AccountDetailsEntity accountOne = new AccountDetailsEntity(new BigInteger("5544337788"), "SGSaving456",
				"Savings", "SGD", 1234.45);
		TransactionDetailsEntity transactionOne = new TransactionDetailsEntity(new BigInteger("66554477"), "Credit",
				new Double("1100.00"), new Double("0.00"), "Deposited", LocalDateTime.now(), accountOne);

		TransactionDetailsEntity transactionTwo = new TransactionDetailsEntity(new BigInteger("66554488"), "Debit",
				new Double("0.00"), new Double("13909.00"), "Withdraw", LocalDateTime.now(), accountOne);
		transactions.add(transactionOne);
		transactions.add(transactionTwo);

		return transactions;
	}
}
