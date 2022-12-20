DROP TABLE Payments;
DROP TABLE AccountOwners;
UPDATE Bankers SET managerId = NULL;
DELETE FROM Bankers;
DROP TABLE LoanOwners;
DROP TABLE Loans;
DROP TABLE Customers;
DROP TABLE Branches;
DROP TABLE Transactions;
DROP TABLE Accounts;
DROP TABLE Bankers;
DROP SEQUENCE loan_seq;
DROP TRIGGER loan_trigger;
DROP SEQUENCE payment_seq;
DROP TRIGGER payment_trigger;
DROP SEQUENCE account_seq;
DROP TRIGGER account_trigger;
DROP SEQUENCE transaction_seq;
DROP TRIGGER transaction_trigger;


-- CREATE SEQUENCE ssn_seq
--     START WITH 100000
--     INCREMENT BY 1
--     NOCACHE;

-- CREATE OR REPLACE TRIGGER ssn_trigger
--     BEFORE INSERT ON Bankers
--     FOR EACH ROW
-- BEGIN
--     SELECT ssn_seq.nextval
--     INTO :new.ssn
--     FROM dual;
-- END;
-- /

delete * from Payments;
delete * from AccountOwners;
delete * from Bankers;
delete * from LoanOwners;
delete * from Loans;
delete * from Customers;
delete * from Branches;
delete * from Transactions;
delete * from Accounts;
delete * from Bankers;
