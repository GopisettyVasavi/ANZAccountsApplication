package com.anz.accounts.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anz.accounts.model.AccountDetailsEntity;

@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetailsEntity, BigInteger>{

}
