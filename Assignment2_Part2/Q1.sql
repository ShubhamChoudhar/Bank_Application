CREATE TABLE Branches (
    name varchar(255) PRIMARY KEY,
    city varchar(255),
    assets varchar(255)
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

CREATE TABLE Customers (
    ssn varchar(255) PRIMARY KEY,
    name varchar(255),
    street varchar(255),
    city varchar(255),
    bankerId varchar(255) REFERENCES Bankers(ssn)
);

CREATE TABLE Loans (
    loanId number PRIMARY KEY,
    customerId varchar(255) REFERENCES Customers(ssn),
    branchName varchar(255) REFERENCES Branches(name),
    loanAmount number
);

CREATE TABLE LoanOwners (
    loanId int REFERENCES Loans(loanId),
    customerId varchar(255) REFERENCES Customers(ssn)
);

CREATE TABLE Accounts (
    accountNo NUMBER PRIMARY KEY,
    accountType VARCHAR(255),
    accountBalance NUMBER,
    interestRate NUMBER,
    overdraft NUMBER
);

CREATE TABLE AccountOwners (
    accountNo number REFERENCES Accounts(accountNo),
    customerId varchar(255) REFERENCES Customers(ssn), 
    PRIMARY KEY (accountNo, customerId)
);

CREATE TABLE Payments (
    paymentId int PRIMARY KEY,
    loanId int REFERENCES Loans(loanId),
    paymentAmount int,
    payment_date DATE
);

CREATE TABLE Transactions (
    transactionId int PRIMARY KEY,
    accountNo int REFERENCES Accounts(accountNo),
    transactionType VARCHAR(255),
    transactionAmount NUMBER,
    transaction_date DATE
);

CREATE SEQUENCE loan_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE OR REPLACE TRIGGER loan_trigger
    BEFORE INSERT ON Loans
    FOR EACH ROW
BEGIN
    SELECT loan_seq.nextval
    INTO :new.loanId
    FROM dual;
END;
/

CREATE SEQUENCE payment_seq
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;

CREATE OR REPLACE TRIGGER payment_trigger
    BEFORE INSERT ON Payments
    FOR EACH ROW
BEGIN
    SELECT payment_seq.nextval
    INTO :new.paymentId
    FROM dual;
END;
/

CREATE SEQUENCE account_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE OR REPLACE TRIGGER account_trigger
    BEFORE INSERT ON Accounts
    FOR EACH ROW
BEGIN
    SELECT account_seq.nextval
    INTO :new.accountNo
    FROM dual;
END;
/

CREATE SEQUENCE transaction_seq
    START WITH 100000
    INCREMENT BY 1
    NOCACHE;

CREATE OR REPLACE TRIGGER transaction_trigger
    BEFORE INSERT ON Transactions
    FOR EACH ROW
BEGIN
    SELECT transaction_seq.nextval
    INTO :new.transactionId
    FROM dual;
END;
/


CREATE OR REPLACE TRIGGER overdraft_trigger
    BEFORE INSERT ON Transactions
    FOR EACH ROW
BEGIN
    IF :new.transactionAmount > 1 THEN
        DBMS_OUTPUT.PUT_LINE('Overdraft limit exceeded');
    END IF;
END;
/


CREATE OR REPLACE TRIGGER overdraft_trigger
    BEFORE INSERT ON Transactions
    FOR EACH ROW
    DECLARE X NUMBER;
    BEGIN
        SELECT overdraft INTO X FROM Accounts WHERE accountNo = :new.accountNo;
    IF :new.transactionAmount >= X
    THEN
        DBMS_OUTPUT.PUT_LINE('Overdraft limit exceeded');
    END IF;
END;
/


CREATE OR REPLACE TRIGGER work_anniversary_trigger
    BEFORE UPDATE OR INSERT ON Bankers
    FOR EACH ROW
    DECLARE X NUMBER;
    BEGIN
        SELECT TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(startDate, 'YYYY') INTO X FROM Bankers WHERE ssn = :new.ssn;
    IF X >= 10
    THEN
        DBMS_OUTPUT.PUT_LINE('Work Anniversary');
    END IF;
END;

SET SERVEROUTPUT ON 

