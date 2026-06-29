public class MVCTest {

    public static void main(String[] args) {

        Student student = new Student("Sindhu", 101, "A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();

        controller.setStudentName("Rahul");
        controller.setStudentGrade("A+");

        System.out.println();

        controller.updateView();
    }
}