public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading " + fileName + " from remote server...");
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}