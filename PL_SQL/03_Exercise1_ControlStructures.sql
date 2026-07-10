BEGIN
    FOR c IN (SELECT CustomerID, DOB FROM Customers) LOOP

        IF TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB)/12) > 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = c.CustomerID;

        END IF;

    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Interest rates updated.');

END;
/

ALTER TABLE Customers
ADD IsVIP VARCHAR2(5);

BEGIN

    FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP

        IF c.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP='TRUE'
            WHERE CustomerID=c.CustomerID;

        ELSE

            UPDATE Customers
            SET IsVIP='FALSE'
            WHERE CustomerID=c.CustomerID;

        END IF;

    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('VIP Status Updated.');

END;
/

BEGIN

    FOR l IN (

        SELECT c.Name,
               l.LoanID,
               l.EndDate

        FROM Customers c
        JOIN Loans l
        ON c.CustomerID=l.CustomerID

        WHERE l.EndDate BETWEEN SYSDATE
                            AND SYSDATE+30

    ) LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: '
            || l.Name
            || ' Loan '
            || l.LoanID
            || ' Due on '
            || TO_CHAR(l.EndDate,'DD-MON-YYYY')
        );

    END LOOP;

END;
/