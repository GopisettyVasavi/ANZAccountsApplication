package com.anz.accounts.web;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anz.accounts.exception.RecordNotFoundException;
import com.anz.accounts.model.AccountResponse;
import com.anz.accounts.model.TransactionResponse;
import com.anz.accounts.service.IAccountDetailsService;

/**
 * This controller class is used to create REST APIs for Account Service.
 * 
 * @author Vasavi
 *
 */
@RestController
@RequestMapping("/accountdetails")
public class AccountDetailsController {

	private static Logger logger = LoggerFactory.getLogger(AccountDetailsController.class);

	@Autowired
	IAccountDetailsService accountService;

	/**
	 * This method will list all the accounts for the given customer.
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping("/api/v1/accounts/{customerId}")
	public ResponseEntity<?> getAccountsByCustomer(@PathVariable BigInteger customerId) {
		if (null != customerId) {
			List<AccountResponse> accounts = null;
			logger.info("Requesting accounts information. ");
			try {
				accounts = accountService.getAccounts(customerId);
				if (null != accounts && accounts.size() > 0) {
					logger.info("Given customer Id is valid and scuccessfully returned accounts list.");
					return new ResponseEntity<>(accounts, HttpStatus.OK);
				} else {
					logger.info("Given customer does not have any accounts associated.");
					return new ResponseEntity<>("Customer does not have accounts.", HttpStatus.OK);
				}
			} catch (RecordNotFoundException e) {
				logger.debug("Exception occured while fetching account details, message: {} ", e.getMessage());
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} else {
			logger.debug("Given Customer id is null.");
			return new ResponseEntity<>("Customer ID can not be null", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method will list all the transactions for the given account
	 * 
	 * @param accountNo
	 * @return
	 */
	@GetMapping("/api/v1/transactions/{accountNo}")
	public ResponseEntity<?> getTransactionsByAccount(@PathVariable BigInteger accountNo) {
		if (accountNo != null) {
			List<TransactionResponse> transactions = null;
			logger.info("Requesting Transaction details.");
			try {
				transactions = accountService.getTransactions(accountNo);
				if (null != transactions && transactions.size() > 0) {
					logger.info("Given Account No. is valid and successfully returned associated transactions list.");
					return new ResponseEntity<>(transactions, HttpStatus.OK);
				} else {
					logger.info("The given Account No. does not have any transactions associated.");
					return new ResponseEntity<>("Account does not have transactions.", HttpStatus.OK);
				}
			} catch (RecordNotFoundException e) {
				logger.debug("Exception occured from Service while fetching transactions, message: {} ", e.getMessage());
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} else {
			logger.debug("Received null Account No. to fetch transactions.");
			return new ResponseEntity<>("Account No. can not be null", HttpStatus.BAD_REQUEST);
		}
	}

}
