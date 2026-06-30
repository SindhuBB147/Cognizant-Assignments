public class EmployeeManagement {

    Employee[] employees = new Employee[10];
    int count = 0;

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
            System.out.println("Employee Added Successfully.");
        } else {
            System.out.println("Employee List is Full.");
        }
    }
    public void searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println(employees[i]);
                return;
            }
        }
        System.out.println("Employee Not Found.");
    }
    public void displayEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }
    public void deleteEmployee(int id) {

        for (int i = 0; i < count; i++) {

            if (employees[i].employeeId == id) {

                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }

                employees[count - 1] = null;
                count--;

                System.out.println("Employee Deleted Successfully.");
                return;
            }
        }

        System.out.println("Employee Not Found.");
    }
}