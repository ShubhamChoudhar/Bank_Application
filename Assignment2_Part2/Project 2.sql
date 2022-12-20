CREATE TABLE Branches (
    name varchar(255) PRIMARY KEY,
    city varchar(255),
    assets varchar(255)
);

CREATE TABLE Customers (
    ssn varchar(255) PRIMARY KEY,
    name varchar(255),
    street varchar(255),
    city varchar(255),
    loanId int,
    bankerId varchar(255) REFERENCES Bankers(ssn)
);
-- Covert to oracle SQL
CREATE TABLE Customers (
    ssn varchar(255) PRIMARY KEY,
    name varchar(255),
    street varchar(255),
    city varchar(255),
    loanId number,
    bankerId varchar(255) REFERENCES Bankers(ssn)
);

CREATE TABLE Loans (
    loanId int PRIMARY KEY,
    branchName varchar(255) REFERENCES Branches(name),
    loanAmount int
);

CREATE TABLE LoanOwners (
    loanId int REFERENCES Loans(loanId),
    customerId varchar(255) REFERENCES Customers(ssn)
);

CREATE TABLE Bankers (
    ssn varchar(255) PRIMARY KEY,
    name varchar(255),
    phone varchar(255),
    dependents varchar(255),
    managerId varchar(255) REFERENCES Bankers(ssn),
    startDate date,
    employmentType varchar(255)
);

CREATE TABLE Accounts (
    accountNo int PRIMARY KEY,
    accountType varchar(255),
    accountBalance int,
    interestRate int,
    overdraft int
);

CREATE TABLE AccountOwners (
    accountNo int REFERENCES Accounts(accountNo),
    customerId varchar(255) REFERENCES Customers(ssn)
);

CREATE TABLE Payments (
    paymentId int PRIMARY KEY,
    loanId int REFERENCES Loans(loanId),
    paymentAmount int,
    date date
);