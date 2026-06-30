public class Main {

    public static void main(String[] args) {

        EmployeeManagement management = new EmployeeManagement();

        management.addEmployee(new Employee(101, "Sindhu", "Developer", 50000));
        management.addEmployee(new Employee(102, "Rahul", "Tester", 40000));
        management.addEmployee(new Employee(103, "Anita", "Manager", 70000));

        System.out.println("\nEmployee Records:");
        management.displayEmployees();

        System.out.println("\nSearch Employee:");
        management.searchEmployee(102);

        System.out.println("\nDelete Employee:");
        management.deleteEmployee(101);

        System.out.println("\nEmployee Records After Deletion:");
        management.displayEmployees();
    }
}