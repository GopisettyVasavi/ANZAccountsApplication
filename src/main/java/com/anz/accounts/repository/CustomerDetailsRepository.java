package com.anz.accounts.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anz.accounts.model.CustomerDetailsEntity;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetailsEntity, BigInteger>{

	

}
