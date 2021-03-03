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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Persistent Entity object for Customer details.
 * 
 * @author Vasavi
 *
 */
@Entity
@Table(name = "TBL_CUSTOMER_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "accounts" })
public class CustomerDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Id
	@Column(name = "cust_id")
	private BigInteger customerId;
	@Column(name = "cust_full_name")
	private String customerFullName;

	@Column(name = "location")
	private String location;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<AccountDetailsEntity> accounts = new ArrayList<AccountDetailsEntity>();

	public BigInteger getCustomerId() {
		return customerId;
	}

	public void setCustomerId(BigInteger customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<AccountDetailsEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDetailsEntity> accounts) {
		this.accounts = accounts;
	}

	public CustomerDetailsEntity() {
		super();
	}
	public CustomerDetailsEntity(BigInteger customerId, String customerFullName, String location,
			List<AccountDetailsEntity> accounts) {
		super();
		this.customerId = customerId;
		this.customerFullName = customerFullName;
		this.location = location;
		this.accounts = accounts;
	}

}
