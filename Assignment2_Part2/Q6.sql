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