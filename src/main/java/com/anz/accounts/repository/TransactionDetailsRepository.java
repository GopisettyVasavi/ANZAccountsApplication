package com.anz.accounts.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anz.accounts.model.TransactionDetailsEntity;
@Repository
public interface TransactionDetailsRepository extends CrudRepository<TransactionDetailsEntity, BigInteger>{

}
