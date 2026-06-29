public class Computer {

    private String cpu;
    private int ram;
    private int storage;
    private String graphicsCard;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    public void display() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
        System.out.println("Graphics Card: " + graphicsCard);
        System.out.println();
    }

    public static class Builder {

        private String cpu;
        private int ram;
        private int storage;
        private String graphicsCard;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}