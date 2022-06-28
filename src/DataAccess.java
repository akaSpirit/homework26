import java.util.Scanner;
//Task1
public class DataAccess {
    Data[] data = {
            new Data("K1", "AAA"),
            new Data("K2", "BBB"),
            new Data("K3", "CCC"),
            new Data("K4", "DDD"),
            new Data("K5", "EEE")
    };

    String input;
    int index;

    void readKey() {
        while(true) {
            System.out.print("Enter key index: ");
            input = new Scanner(System.in).nextLine();
            try {
                checkIndex(input);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        index = Integer.parseInt(input);
        System.out.println(data[index].getKey());
    }

    void writeKey() {
        System.out.print("Enter new key: ");
        String newKey = new Scanner(System.in).nextLine();
        data[index].setKey(newKey);
        System.out.println(data[index].getKey());
    }

    void checkIndex(String input) throws Exception {
        if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= data.length) {
            throw new Exception("No such index.");
        }
    }

    void readValue() {
        while(true) {
            System.out.print("Enter value index: ");
            input = new Scanner(System.in).nextLine();
            try {
                checkIndex(input);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        index = Integer.parseInt(input);
        System.out.println(data[index].getValue());
    }

    void writeValue() {
        System.out.print("Enter new value: ");
        String newValue = new Scanner(System.in).nextLine();
        data[index].setValue(newValue);
        System.out.println(data[index].getValue());
    }
}
