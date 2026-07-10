DECLARE

    CURSOR GenerateMonthlyStatements IS
    SELECT *
    FROM Transactions
    WHERE TRUNC(TransactionDate,'MM') = TRUNC(SYSDATE,'MM');

    t Transactions%ROWTYPE;

BEGIN

    OPEN GenerateMonthlyStatements;

    LOOP

        FETCH GenerateMonthlyStatements INTO t;

        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Transaction ID : ' || t.TransactionID ||
            '  Account ID : ' || t.AccountID ||
            '  Amount : ' || t.Amount ||
            '  Type : ' || t.TransactionType
        );

    END LOOP;

    CLOSE GenerateMonthlyStatements;

END;
/

DECLARE

    CURSOR ApplyAnnualFee IS
    SELECT AccountID
    FROM Accounts;

    v_id NUMBER;

BEGIN

    OPEN ApplyAnnualFee;

    LOOP

        FETCH ApplyAnnualFee INTO v_id;

        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = v_id;

    END LOOP;

    CLOSE ApplyAnnualFee;

    COMMIT;

END;
/

SELECT * FROM Accounts;

DECLARE

    CURSOR UpdateLoanInterestRates IS
    SELECT LoanID, InterestRate
    FROM Loans;

    v_id NUMBER;
    v_rate NUMBER;

BEGIN

    OPEN UpdateLoanInterestRates;

    LOOP

        FETCH UpdateLoanInterestRates
        INTO v_id, v_rate;

        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        UPDATE Loans
        SET InterestRate = v_rate + 0.5
        WHERE LoanID = v_id;

    END LOOP;

    CLOSE UpdateLoanInterestRates;

    COMMIT;

END;
/

SELECT * FROM Loans;