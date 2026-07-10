CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        id NUMBER,
        name VARCHAR2,
        dob DATE,
        bal NUMBER
    );

    PROCEDURE UpdateCustomer(
        id NUMBER,
        bal NUMBER
    );

    FUNCTION GetBalance(
        id NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        id NUMBER,
        name VARCHAR2,
        dob DATE,
        bal NUMBER
    )
    IS
    BEGIN
        INSERT INTO Customers
        VALUES(id,name,dob,bal,SYSDATE,NULL);
    END;

    PROCEDURE UpdateCustomer(
        id NUMBER,
        bal NUMBER
    )
    IS
    BEGIN
        UPDATE Customers
        SET Balance = bal
        WHERE CustomerID = id;
    END;

    FUNCTION GetBalance(
        id NUMBER
    ) RETURN NUMBER
    IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = id;

        RETURN v_balance;
    END;

END CustomerManagement;
/


CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        id NUMBER,
        empname VARCHAR2,
        position VARCHAR2,
        salary NUMBER,
        dept VARCHAR2
    );

    PROCEDURE UpdateEmployee(
        id NUMBER,
        salary NUMBER
    );

    FUNCTION AnnualSalary(
        id NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/


CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        id NUMBER,
        empname VARCHAR2,
        position VARCHAR2,
        salary NUMBER,
        dept VARCHAR2
    )
    IS
    BEGIN
        INSERT INTO Employees
        VALUES(
            id,
            empname,
            position,
            salary,
            dept,
            SYSDATE
        );
    END;

    PROCEDURE UpdateEmployee(
        id NUMBER,
        salary NUMBER
    )
    IS
    BEGIN
        UPDATE Employees
        SET Salary = salary
        WHERE EmployeeID = id;
    END;

    FUNCTION AnnualSalary(
        id NUMBER
    ) RETURN NUMBER
    IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = id;

        RETURN v_salary * 12;
    END;

END EmployeeManagement;
/


CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        id NUMBER,
        cust NUMBER,
        acctype VARCHAR2,
        bal NUMBER
    );

    PROCEDURE CloseAccount(
        id NUMBER
    );

    FUNCTION TotalBalance(
        cust NUMBER
    ) RETURN NUMBER;

END AccountOperations;
/



CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        id NUMBER,
        cust NUMBER,
        acctype VARCHAR2,
        bal NUMBER
    )
    IS
    BEGIN
        INSERT INTO Accounts
        VALUES(
            id,
            cust,
            acctype,
            bal,
            SYSDATE
        );
    END;

    PROCEDURE CloseAccount(
        id NUMBER
    )
    IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = id;
    END;

    FUNCTION TotalBalance(
        cust NUMBER
    ) RETURN NUMBER
    IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = cust;

        RETURN v_total;
    END;

END AccountOperations;
/

BEGIN
    CustomerManagement.AddCustomer(
        10,
        'Rahul',
        TO_DATE('2000-01-15','YYYY-MM-DD'),
        8000
    );
END;
/

BEGIN
    CustomerManagement.UpdateCustomer(
        10,
        12000
    );
END;
/

SELECT CustomerManagement.GetBalance(10)
FROM Dual;


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

SELECT EmployeeManagement.AnnualSalary(10)
FROM Dual;


BEGIN
    AccountOperations.OpenAccount(
        10,
        10,
        'Savings',
        5000
    );
END;
/

SELECT AccountOperations.TotalBalance(10)
FROM Dual;

BEGIN
    AccountOperations.CloseAccount(10);
END;
/

SELECT *
FROM Accounts;