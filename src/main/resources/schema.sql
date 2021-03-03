
DROP TABLE IF EXISTS TBL_CUSTOMER_DETAILS;
 
DROP TABLE IF EXISTS TBL_ACCOUNT_DETAILS;
DROP TABLE IF EXISTS TBL_TRANSACTION_DETAILS;


CREATE TABLE TBL_CUSTOMER_DETAILS (
cust_id BIGINT  PRIMARY KEY,
  cust_full_name VARCHAR(250) NOT NULL,
  location VARCHAR(250)    
);

CREATE TABLE TBL_ACCOUNT_DETAILS (
 account_no BIGINT PRIMARY KEY,
  account_name VARCHAR(250) NOT NULL,
  account_type VARCHAR(250) NOT NULL,
  currency char(3) NOT NULL,
  account_balance DECIMAL,
  cust_id BIGINT,
  foreign key (cust_id) references TBL_CUSTOMER_DETAILS(cust_id)
);

CREATE TABLE TBL_TRANSACTION_DETAILS (
 transaction_id BIGINT PRIMARY KEY,
 traction_type VARCHAR(20) NOT NULL,
 credit_amount DECIMAL,
 debit_amount DECIMAL ,
 transaction_narrative VARCHAR(250),
 transaction_date TIMESTAMP,
 account_no BIGINT,
  foreign key (account_no) references TBL_ACCOUNT_DETAILS(account_no)
);



