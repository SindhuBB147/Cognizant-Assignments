public class TaskLinkedList {

    Task head = null;
    public void addTask(int taskId, String taskName, String status) {

        Task newTask = new Task(taskId, taskName, status);

        if (head == null) {
            head = newTask;
        } else {
            Task temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newTask;
        }

        System.out.println("Task Added Successfully.");
    }
    public void searchTask(int taskId) {

        Task temp = head;

        while (temp != null) {

            if (temp.taskId == taskId) {
                System.out.println(temp);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task Not Found.");
    }
    public void displayTasks() {

        Task temp = head;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public void deleteTask(int taskId) {

        if (head == null) {
            System.out.println("Task List is Empty.");
            return;
        }

        if (head.taskId == taskId) {
            head = head.next;
            System.out.println("Task Deleted Successfully.");
            return;
        }

        Task temp = head;

        while (temp.next != null && temp.next.taskId != taskId) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Task Not Found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Task Deleted Successfully.");
        }
    }
}