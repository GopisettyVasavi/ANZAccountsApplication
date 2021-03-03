package com.anz.accounts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * 
 */
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anz.accounts.AccountsApplication;
import com.anz.accounts.exception.RecordNotFoundException;
import com.anz.accounts.model.AccountResponse;
import com.anz.accounts.model.TransactionResponse;
import com.anz.accounts.service.IAccountDetailsService;

/**
 * This test class will test all the REST APIs created for
 * TelstraPhoneApplication Application.
 * 
 * @author Vasavi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AccountsApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class AccountDetailsControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	IAccountDetailsService service;

	/**
	 * This test will check whether the return code is 200 when fetching accounts
	 * for the given customer ID Tests URL: /accounts/{customerId} Expect size>1 and
	 * status:200
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenCustomerId_whengetAccounts_thenStatus200() throws Exception {
		BigInteger customerNo = new BigInteger("23423");

		Mockito.when(service.getAccounts(customerNo)).thenReturn(populateAccounts());

		mvc.perform(get("/accountdetails/api/v1/accounts/" + customerNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1)))).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.size()", is(populateAccounts().size())));

	}

	/**
	 * This test will check whether the return code is 200 when fetching accounts
	 * for the given customer ID where customer exists but there are no accounts for
	 * the customer. Tests URL: /accounts/{customerId} status:200
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenCustomerIdWithNoAccounts_whengetAccounts_thenStatus200() throws Exception {
		BigInteger customerNo = new BigInteger("23423");

		Mockito.when(service.getAccounts(customerNo)).thenReturn(new ArrayList<AccountResponse>());

		mvc.perform(get("/accountdetails/api/v1/accounts/" + customerNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	/**
	 * This test will check whether the return code is 404 when fetching accounts
	 * for the non existent customer ID Tests URL: /accounts/{customerId} Expect
	 * status:404
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenInvalidCustomerId_whengetAccounts_thenStatus404() throws Exception {

		BigInteger customerNo = new BigInteger("342382");
		Mockito.when(service.getAccounts(customerNo)).thenThrow(new RecordNotFoundException());
		mvc.perform(get("/accountdetails/api/v1/accounts/" + customerNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is(404));

	}

	/**
	 * This test will check whether the return code is 200 and transactions list
	 * size>0 when fetching transactions for the given customer ID where account
	 * exists and it has transactions. Tests URL:
	 * /transactions?accountNo={accountNo} status:200
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenAccountNo_whengetTransactions_thenStatus200() throws Exception {

		BigInteger accountNo = new BigInteger("23423");

		Mockito.when(service.getTransactions(accountNo)).thenReturn(populateTransactions());
		mvc.perform(get("/accountdetails/api/v1/transactions/" + accountNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1)))).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.size()", is(populateTransactions().size())));

	}

	/**
	 * This test will check whether the return code is 200 when fetching
	 * transactions for the given customer ID where account exists but there are no
	 * transactions for the account. Tests URL: /transactions?accountNo={accountNo}
	 * status:200
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenAccountNoWithNoTransactions_thenStatus200() throws Exception {

		BigInteger accountNo = new BigInteger("585304513");

		Mockito.when(service.getTransactions(accountNo)).thenReturn(new ArrayList<TransactionResponse>());

		mvc.perform(get("/accountdetails/api/v1/transactions/" + accountNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	/**
	 * This test will check whether the return code is 404 when fetching
	 * transactions for the non existent customer ID Tests URL:
	 * /transactions?accountNo={accountNo} Expect status:404
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenInvalidAccountNo_whengetTransactions_thenStatus404() throws Exception {

		BigInteger accountNo = new BigInteger("585304513");
		Mockito.when(service.getTransactions(accountNo)).thenThrow(new RecordNotFoundException());
		mvc.perform(get("/accountdetails/api/v1/transactions/" + accountNo).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is(404));

	}

	/**
	 * This method will create Account details objects and return the list.
	 * 
	 * @return
	 */
	private List<AccountResponse> populateAccounts() {
		List<AccountResponse> accounts = new ArrayList<AccountResponse>();

		AccountResponse accountOne = new AccountResponse(new BigInteger("5544337788"));
		AccountResponse accountTwo = new AccountResponse(new BigInteger("5544337799"));
		accounts.add(accountOne);
		accounts.add(accountTwo);

		return accounts;
	}

	/**
	 * This method will populate transactions list and return.
	 * 
	 * @return
	 */
	private List<TransactionResponse> populateTransactions() {
		List<TransactionResponse> transactions = new ArrayList<TransactionResponse>();
		TransactionResponse transactionOne = new TransactionResponse(new BigInteger("66554477"), "SGSaving456", "SGD",
				new Double("1100.00"), new Double("0.00"), "Credit", "Deposited");

		TransactionResponse transactionTwo = new TransactionResponse(new BigInteger("66554478"), "AUSaving456", "AUD",
				new Double("1100.00"), new Double("0.00"), "Debit", "Withdraw");
		TransactionResponse transactionThree = new TransactionResponse(new BigInteger("66554477"), "SGCurrent456",
				"SGD", new Double("1100.00"), new Double("0.00"), "Credit", "Deposited");

		TransactionResponse transactionFour = new TransactionResponse(new BigInteger("66554478"), "AUCurrent456", "AUD",
				new Double("1100.00"), new Double("0.00"), "Debit", "Withdraw");
		transactions.add(transactionOne);
		transactions.add(transactionTwo);
		transactions.add(transactionThree);
		transactions.add(transactionFour);

		return transactions;
	}

}
