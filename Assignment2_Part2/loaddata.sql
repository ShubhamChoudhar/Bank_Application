-- Load data for branches table
-- Set date to be ion yyyy-mm-dd format
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';

INSERT INTO Branches (name, city, assets)
 VALUES ('SBI-Bangalore', 'Bangalore', 100000);

INSERT INTO Branches (name, city, assets)
    VALUES ('SBI-Mumbai', 'Mumbai', 100000);

INSERT INTO Branches (name, city, assets)
    VALUES ('SBI-Delhi', 'Delhi', 100000);

INSERT INTO Branches (name, city, assets)
    VALUES ('SBI-Chennai', 'Chennai', 100000);

INSERT INTO Branches (name, city, assets)
    VALUES ('SBI-Kolkata', 'Kolkata', 100000);

-- Loading data for Bankers table
insert into Bankers (ssn, name, phone, dependents, managerId, startDate, employmentType) values ('391-61-1149', 'Goddard Spawell', '8056739634', 'Goddard', NULL, '2021-05-02', 'Employee');
insert into Bankers (ssn, name, phone, dependents, managerId, startDate, employmentType) values ('472-25-5860', 'Marc Cowely', '5709398119', 'Marc', '391-61-1149', '2021-07-23', 'Employee');
insert into Bankers (ssn, name, phone, dependents, managerId, startDate, employmentType) values ('121-21-6889', 'Joane McCosh', '7029626626', 'Joane', '391-61-1149', '2021-12-11', 'Intern');
insert into Bankers (ssn, name, phone, dependents, managerId, startDate, employmentType) values ('602-18-3403', 'Dorine Elsie', '1761191200', 'Dorine', '391-61-1149', '2021-11-30', 'Intern');
insert into Bankers (ssn, name, phone, dependents, managerId, startDate, employmentType) values ('147-59-1755', 'Bernette Rembaud', '5445561531', 'Bernette', '391-61-1149', '2021-04-27', 'Employee');

-- Loading data for Customers table
-- insert into Customers (ssn, name, street, city, bankerId) values ('391-61-1149', 'Goddard Spawell', '8056739634', 'Goddard', '391-61-1149');
insert into Customers (ssn, name, street, city, bankerId) values ('512-92-9137', 'Person One', 'Random Street', 'City A', '391-61-1149');
insert into Customers (ssn, name, street, city, bankerId) values ('214-32-3091', 'Person Two', 'Random Street', 'City B', '391-61-1149');
insert into Customers (ssn, name, street, city, bankerId) values ('828-35-0350', 'Person Three', 'Random Street', 'City A', '391-61-1149');
insert into Customers (ssn, name, street, city, bankerId) values ('137-83-8646', 'Person Four', 'Random Street', 'City C', '391-61-1149');
insert into Customers (ssn, name, street, city, bankerId)
 values ('215-52-6384', 'Person Five', 'Random Street', 'City A', '391-61-1149');

-- Loans table
insert into Loans (customerId, branchName, loanAmount)
values ('512-92-9137', 'SBI-Bangalore', 100000);

INSERT INTO Loans (customerId, branchName, loanAmount)
    VALUES ('512-92-9137', 'SBI-Bangalore', 100000);

INSERT INTO Loans (customerId, branchName, loanAmount)
    VALUES ('512-92-9137', 'SBI-Bangalore', 100,000);

INSERT INTO Loans (customerId, branchName, loanAmount)
    VALUES ('512-92-9137', 'SBI-Bangalore', 100000);

INSERT INTO Loans (customerId, branchName, loanAmount)
    VALUES ('512-92-9137', 'SBI-Bangalore', 100000);


SELECT loanId from Loans;
-- LoanOwners
INSERT INTO LoanOwners (loanId, customerId) VALUES (1, '512-92-9137');
INSERT INTO LoanOwners (loanId, customerId) VALUES (2, '512-92-9137');
INSERT INTO LoanOwners (loanId, customerId) VALUES (3, '512-92-9137');
INSERT INTO LoanOwners (loanId, customerId) VALUES (4, '512-92-9137');
INSERT INTO LoanOwners (loanId, customerId) VALUES (5, '512-92-9137');

-- Payments
INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES (1, 10,00000, '2021-05-02');
INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES (2, 1000000, '2021-05-02');
INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES (3, 1000000, '2021-05-02');
INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES (4, 1000000, '2021-05-02');
INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES (5, 1000000, '2021-05-02');


-- Accounts
INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft) VALUES ('Savings', 100000, 0.1, 0);
INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft) VALUES ('Savings', 100000, 0.1, 0);
INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft) VALUES ('Checkings', 100000, 0.1, 0);
INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft) VALUES ('Savings', 100000, 0.1, 0);
INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft) VALUES ('Savings', 100000, 0.1, 0);

SELECT accountNo from Accounts;

-- AccountOwners
INSERT INTO AccountOwners (accountNo, customerId) VALUES (1, '512-92-9137');
INSERT INTO AccountOwners (accountNo, customerId) VALUES (2, '512-92-9137');
INSERT INTO AccountOwners (accountNo, customerId) VALUES (3, '512-92-9137');
INSERT INTO AccountOwners (accountNo, customerId) VALUES (4, '512-92-9137');
INSERT INTO AccountOwners (accountNo, customerId) VALUES (5, '512-92-9137');


-- Transactions
INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES (1, 'Deposit', 100000, '2022-04-16');
INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES (2, 'Deposit', 100000, '2022-04-16');
INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES (1, 'Deposit', 100000, '2022-04-16');
INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES (3, 'Deposit', 100000, '2022-04-16');
INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES (4, 'Deposit', 100000, '2022-04-16');

-- @deleteTables2
-- @createTables2


-- @loaddata
-- axk3905
-- G3radTs1fdr7d

-- @createTables2
-- @loaddata
-- @deleteTables2

--  Get customers ssn, name and Count of Loans who have more than one loan





