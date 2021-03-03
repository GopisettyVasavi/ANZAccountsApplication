INSERT INTO 
	TBL_CUSTOMER_DETAILS (cust_id, cust_full_name, location) 
VALUES
  	(23423, 'Customer1', 'Melbourne'),
  	(87878, 'Customer2', 'Sydney'),
  	(98986, 'Customer3', 'Brsibane'),
  	(87654, 'Customer4', 'Melbourne'),
  	(98973, 'Customer5', 'Melbourne'),
  	(23412, 'Customer6', 'Melbourne'),
  	(53451, 'Customer7', 'Melbourne'),
  	(56345, 'Customer8', 'Melbourne'),
  	(98561, 'Customer9', 'Melbourne'),
  	(89434, 'Customer10', 'Melbourne');
  	
  	
INSERT INTO 
	TBL_ACCOUNT_DETAILS (account_no, account_name, account_type,currency,account_balance, cust_id) 
VALUES
(585309209, 'SGSaving726', 'Savings', 'SGD', 34330.51 , 23423),
(575309209, 'AUSavings933', 'Savings', 'AUD', 23121.33, 23423),
(585309210, 'AUCurrent433', 'Current', 'AUD', 12330.12, 23423),
(585309211, 'SGCurrent166', 'Current', 'SGD', 56556.23, 23423),
(585309212, 'AUCurrent374', 'Current', 'AUD', 787899.40, 23423),

(585309309, 'SGSaving726', 'Savings', 'SGD', 13300.33, 87878),
(585309310, 'AUSaving253', 'Savings', 'AUD', 455500.90, 87878),
(585309311, 'AUCurrent787', 'Current', 'AUD', 440023.50, 87878),
(585309312, 'SGCurrent434', 'Current', 'SGD', 7676763.23, 87878),
(585304009, 'AUSaving234', 'Savings', 'AUD', 78000.00, 87878),

(585309409, 'SGSaving726', 'Savings', 'SGD', 13300.33, 98973),
(585309410, 'SGCurrent726', 'Current', 'SGD', 423500.90, 98973),
(585309411, 'AUSaving786', 'Savings', 'AUD', 440023.50, 98973),
(585309412, 'AUCurrent432', 'Current', 'AUD', 7676763.23, 98973),


(585309509, 'SGSaving736', 'Savings', 'SGD', 13300.33, 98561),
(585309510, 'AUSaving345', 'Savings', 'AUD', 455500.90, 98561),
(585309511, 'SGCurrent786', 'Current', 'SGD', 440023.50, 98561),
(585309512, 'AUCurrent678', 'Current', 'AUD', 7676763.23, 98561),
(585304513, 'SGSaving796', 'Savings', 'SGD', 7808700.00, 98561);


 
INSERT INTO 
	TBL_TRANSACTION_DETAILS (transaction_id, traction_type, credit_amount,debit_amount,transaction_narrative,transaction_date, account_no) 
VALUES(112232, 'Credit', 1100.00, 0.00, 'Deposited', '2021-02-24 09:30:00', 585309209),

(112233, 'Credit', 1100.00, 0.00,'Deposited', '2021-02-24 10:30:00', 585309510),
(112234, 'Debit', 0.00, 100.00, 'Withdraw', '2021-02-24 01:20:00', 585309510),

(112235, 'Credit', 12000.00, 0.00, 'Deposited', '2021-02-24 04:25:00', 585309410),
(112236, 'Debit', 0.00, 11000.00, 'Withdraw', '2021-02-24 08:30:00', 585309410),
(112237, 'Credit', 3400.00, 0.00, 'Deposited', '2021-02-24 09:00:00', 585309410),

(112238, 'Credit', 3400.12, 0.00, 'Deposited', '2021-02-24 08:40:00', 585309412),
(112239, 'Debit', 0.00, 56000.00, 'Withdraw', '2021-02-24 09:50:00', 585309412),
(112240, 'Debit', 0.00, 1100.00, 'Withdraw', '2021-02-24 08:05:00', 585309412),
(112241, 'Credit', 100.00,0.00 , 'Deposited', '2021-02-24 07:30:00', 585309412),

(112242, 'Credit', 1100.00, 0.00, 'Deposited', '2021-02-24 06:04:00', 585309510),
(112243, 'Debit', 0.00, 1150.00, 'Withdraw', '2021-02-24 08:07:00', 585309510),
(112244, 'Credit', 2300.00,0.00,  'Deposited', '2021-02-24 11:00:00', 585309510),
(112245, 'Debit', 0.00, 450.00, 'Withdraw', '2021-02-24 10:11:00', 585309510),

(112251, 'Credit', 11000.00,0.00 , 'Deposited', '2021-02-24 00:15:00', 585309210),
(112252, 'Credit', 6709.00,0.00 , 'Deposited', '2021-02-24 09:55:00', 585309210);
