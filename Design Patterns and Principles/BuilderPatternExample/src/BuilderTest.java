public class BuilderTest {

    public static void main(String[] args) {

        Computer computer1 = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam(8)
                .setStorage(512)
                .setGraphicsCard("NVIDIA GTX 1650")
                .build();

        Computer computer2 = new Computer.Builder()
                .setCpu("AMD Ryzen 7")
                .setRam(16)
                .setStorage(1024)
                .setGraphicsCard("NVIDIA RTX 4060")
                .build();

        System.out.println("Computer 1");
        computer1.display();

        System.out.println("Computer 2");
        computer2.display();
    }
}