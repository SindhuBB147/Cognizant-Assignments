CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
)
RETURN NUMBER
IS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE,p_dob)/12);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_amount NUMBER,
    p_rate NUMBER,
    p_years NUMBER
)
RETURN NUMBER
IS
    emi NUMBER;
    r NUMBER;
    n NUMBER;
BEGIN
    r := p_rate/1200;
    n := p_years*12;

    emi := (p_amount*r*POWER(1+r,n)) /
           (POWER(1+r,n)-1);

    RETURN ROUND(emi,2);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account NUMBER,
    p_amount NUMBER
)
RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account;

    RETURN v_balance >= p_amount;
END;
/

SELECT CalculateAge(
    TO_DATE('1985-05-15','YYYY-MM-DD')
) AS Age
FROM Dual;

SELECT CalculateMonthlyInstallment(
    500000,
    8,
    5
) AS EMI
FROM Dual;

DECLARE
    result BOOLEAN;
BEGIN
    result := HasSufficientBalance(1,500);

    IF result THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient Balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance');
    END IF;
END;
/