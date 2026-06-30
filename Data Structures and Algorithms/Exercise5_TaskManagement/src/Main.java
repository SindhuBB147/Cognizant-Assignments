public class Main {

    public static void main(String[] args) {

        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addTask(101, "Design UI", "Pending");
        taskList.addTask(102, "Write Code", "In Progress");
        taskList.addTask(103, "Testing", "Pending");

        System.out.println("\nTask List:");
        taskList.displayTasks();

        System.out.println("\nSearch Task:");
        taskList.searchTask(102);

        System.out.println("\nDelete Task:");
        taskList.deleteTask(101);

        System.out.println("\nTask List After Deletion:");
        taskList.displayTasks();
    }
}