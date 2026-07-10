CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_id NUMBER,
        p_balance NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_id NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    )
    IS
    BEGIN
        INSERT INTO Customers
        VALUES(p_id,p_name,p_dob,p_balance,SYSDATE,NULL);
    END;

    PROCEDURE UpdateCustomer(
        p_id NUMBER,
        p_balance NUMBER
    )
    IS
    BEGIN
        UPDATE Customers
        SET Balance=p_balance
        WHERE CustomerID=p_id;
    END;

    FUNCTION GetCustomerBalance(
        p_id NUMBER
    )
    RETURN NUMBER
    IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID=p_id;

        RETURN v_balance;
    END;

END CustomerManagement;
/

BEGIN
    CustomerManagement.AddCustomer(
        20,
        'Rohit',
        TO_DATE('1998-05-15','YYYY-MM-DD'),
        9000
    );
END;
/

BEGIN
    CustomerManagement.UpdateCustomer(
        20,
        12000
    );
END;
/

SELECT CustomerManagement.GetCustomerBalance(20)
FROM Dual;

CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    );

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_id NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    )
    IS
    BEGIN
        INSERT INTO Employees
        VALUES(
            p_id,
            p_name,
            p_position,
            p_salary,
            p_department,
            SYSDATE
        );
    END;

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    )
    IS
    BEGIN
        UPDATE Employees
        SET Salary=p_salary
        WHERE EmployeeID=p_id;
    END;

    FUNCTION CalculateAnnualSalary(
        p_id NUMBER
    )
    RETURN NUMBER
    IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID=p_id;

        RETURN v_salary*12;
    END;

END EmployeeManagement;
/

BEGIN
    EmployeeManagement.HireEmployee(
        10,
        'David',
        'Tester',
        40000,
        'QA'
    );
END;
/

BEGIN
    EmployeeManagement.UpdateEmployee(
        10,
        45000
    );
END;
/

SELECT EmployeeManagement.CalculateAnnualSalary(10)
FROM Dual;

CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        p_accountid NUMBER,
        p_customerid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_accountid NUMBER
    );

    FUNCTION GetTotalBalance(
        p_customerid NUMBER
    )
    RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_accountid NUMBER,
        p_customerid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    )
    IS
    BEGIN
        INSERT INTO Accounts
        VALUES(
            p_accountid,
            p_customerid,
            p_type,
            p_balance,
            SYSDATE
        );
    END;

    PROCEDURE CloseAccount(
        p_accountid NUMBER
    )
    IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID=p_accountid;
    END;

    FUNCTION GetTotalBalance(
        p_customerid NUMBER
    )
    RETURN NUMBER
    IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance)
        INTO v_total
        FROM Accounts
        WHERE CustomerID=p_customerid;

        RETURN v_total;
    END;

END AccountOperations;
/

BEGIN
    AccountOperations.OpenAccount(
        10,
        20,
        'Savings',
        5000
    );
END;
/

SELECT AccountOperations.GetTotalBalance(20)
FROM Dual;

BEGIN
    AccountOperations.CloseAccount(10);
END;
/

SELECT * FROM Accounts;