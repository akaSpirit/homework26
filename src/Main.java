public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DB dataBase = new DB();
        while (true) {
            dataBase.action();
        }
    }
}
