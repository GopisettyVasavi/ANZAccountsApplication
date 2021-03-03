
This project can be imported into any IDE with the pom file as Maven Project. 
In the application.properties file, port number has been changed to 8070. 
Change to a different one if required. 
**************************************************************************************************************************
This used h2 database, which does not require any extra setup. 
3 tables have been created to use in the application. 
a. TBL_CUSTOMER_DETAILS: Stores Customer information like Customer ID, Name and Location and Customer Id is primary key.
b. TBL_ACCOUNT_DETAILS: Stores account details where AccountNo is the primary key and CustomerID as foreign key.

Customer to Account is 1 to M relation.

c. TBL_TRANSACTION_DETAILS: Stores transaction details where Transaction Id is the primary key and Account No as foreign key. 
Account to Transaction is 1 to M relation.

Required data will be preloaded to the tables as soon as the application starts. schema.sql and data.sql will have the tables and INSERT queries.

***********************************************************************************************************************************************
Understanding/Assumptions:

There is a list of customers who has multiple accounts. Each account will have multiple transactions.
Formatting output for balances is not done assuming presentation layer will handle it.
Formatting of dates is done.

Accounts Application has 2 REST API implementations.

1. View Accounts : 
End point: /accountdetails/api/v1/accounts/{customerId}
Input: accountNo 
Output: List of Transactions 
Method type: GET 
Status codes: Code: 200 (If the given customerId is valid). 
Code: 404 (If customerId is not found) 
Code: 400 (If the customerId is invalid or null)

Testcase1: Valid customer with accounts
![](/images/image1.png?raw=true)

Testcase2: Customer does not exist.

![](/images/image2.png?raw=true)

Testcase 3: Invalid customer

![](/images/image3.png?raw=true)


2. View Transactions End point: localhost:8070/accountdetails/api/v1/transactions/{accountNo}

 Input: accountNo 
Output: List of Transactions 
Method type: GET 
Status codes: Code: 200 (If the given accountNo is valid). 
Code: 404 (If accountNo is not found) 
Code: 400 (If the accountNo is invalid or null)

Test case 1: 
output: Valid account with transactions

![](/images/image4.png?raw=true)
Test case 2: 
Valid Account but no transactions 

![](/images/image5.png?raw=true)

Test case 3: 
Invalid account 

![](/images/image6.png?raw=true)

