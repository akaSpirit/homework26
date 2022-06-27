public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DataAccess da = new DataAccess();
        while (true) {
            da.readKey();
            da.writeKey();;
            da.readValue();
            da.writeValue();
        }
    }
}
