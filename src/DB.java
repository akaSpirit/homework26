import java.util.Arrays;
import java.util.Scanner;

public class DB implements DataBase {
    private boolean connection;
    private Data[] data = FileService.readFile();

    @Override
    public void openConnection() throws Exception {
        if (connection)
            throw new Exception("Already connected");
        connection = true;
        System.out.println("Connection is opened...");
    }

    @Override
    public void closeConnection() throws Exception {
        if (!connection)
            throw new Exception("Connection already closed");
        connection = false;
        System.out.println("Connection is closed...");
    }

    @Override
    public void checkConnection() throws Exception {
        if (!connection)
            throw new Exception("Not connected");
    }

    @Override
    public void readRecordByIndex() {
        String input;
        try {
            checkConnection();
            while (true) {
                System.out.printf("Enter key index (0..%s): ", data.length - 1);
                input = new Scanner(System.in).nextLine();
                try {
                    checkIndex(input);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            int index = Integer.parseInt(input);
            printRecords(index, index + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void isRecordByKey(String key) throws Exception {
        int counter = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].getKey().equals(key)) {
                System.out.println("Key found");
                return;
            }
            counter++;
        }
        if (counter == data.length)
            throw new Exception("Key not found");
    }


    @Override
    public void readRecordByKey() {
        try {
            checkConnection();
            while (true) {
                System.out.print("Enter key: ");
                try {
                    String key = new Scanner(System.in).nextLine();
                    isRecordByKey(key);
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].getKey().equals(key)) {
                            printRecords(i, i + 1);
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Data[] readRecords(String str1, String str2) {
        try {
            checkIndexes(str1, str2);
            return Arrays.copyOfRange(data, Integer.parseInt(str1), Integer.parseInt(str1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void countRecords() {
        try {
            checkConnection();
            System.out.printf("Records quantity: %s%n", data.length);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addRecord() {
        try {
            checkConnection();
            data = Arrays.copyOf(data, data.length + 1);
            System.out.print("Enter key: ");
            String key = new Scanner(System.in).nextLine();
            System.out.print("Enter value: ");
            String value = new Scanner(System.in).nextLine();
            Data newData = new Data(key, value);
            data[data.length - 1] = newData;
            printRecords(data.length - 1, data.length);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateValueByIndex() {
        String input;
        try {
            checkConnection();
            while (true) {
                System.out.printf("Enter key index (0..%s): ", data.length - 1);
                input = new Scanner(System.in).nextLine();
                try {
                    checkIndex(input);
                    int index = Integer.parseInt(input);
                    printRecords(index, index + 1);
                    System.out.print("Enter new value: ");
                    data[index].setValue(new Scanner(System.in).nextLine());
                    printRecords(index, index + 1);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateValueByKey() {
        try {
            checkConnection();
            while (true) {
                System.out.print("Enter key: ");
                try {
                    String key = new Scanner(System.in).nextLine();
                    isRecordByKey(key);
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].getKey().equals(key)) {
                            printRecords(i, i + 1);
                            System.out.print("Enter new value: ");
                            data[i].setValue(new Scanner(System.in).nextLine());
                            printRecords(i, i + 1);
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkIndex(String input) throws Exception {
        if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= data.length) {
            throw new Exception("No such index.");
        }
    }

    private void checkIndexes(String str1, String str2) throws Exception {
        if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
            throw new Exception("End index must be higher than start index");
        }
    }

    public void printRecords(int num1, int num2) {
        try {
            checkConnection();
            System.out.println("+-------+-----------------+--------------------------------+");
            System.out.println("| Index |       Key       |              Value             |");
            System.out.println("+-------+-----------------+--------------------------------+");
            for (int i = num1; i < num2; i++) {
                System.out.printf("|  %2s   | %-15s | %-30s |%n", i, data[i].getKey(), data[i].getValue());
                System.out.println("+-------+-----------------+--------------------------------+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printMenu() {
        System.out.println("""
                \nChoose action:
                1. Open connection
                2. Close connection
                3. Read record by index
                4. Read record by key
                5. Read records (start index, end index)
                6. Count records
                7. Add record
                8. Update value by index
                9. Update value by key
                10. Print records
                """);
    }

    public void action() {
        int action;
        while (true) {
            printMenu();
            System.out.print("Enter number of action (1..10): ");
            String input = new Scanner(System.in).nextLine();
            try {
                checkInput(input);
                action = Integer.parseInt(input);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        switch (action) {
            case 1:
                try {
                    openConnection();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    closeConnection();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                readRecordByIndex();
                break;
            case 4:
                readRecordByKey();
                break;
            case 5:
                try {
                    checkConnection();
                    String str1;
                    String str2;
                    while (true) {
                        try {
                            System.out.print("Enter start index: ");
                            str1 = new Scanner(System.in).nextLine();
                            checkIndex(str1);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Enter end index: ");
                            str2 = new Scanner(System.in).nextLine();
                            checkIndex(str2);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    readRecords(str1, str2);
                    printRecords(Integer.parseInt(str1), Integer.parseInt(str2) + 1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                countRecords();
                break;
            case 7:
                addRecord();
                FileService.writeFile(data);
                break;
            case 8:
                updateValueByIndex();
                FileService.writeFile(data);
                break;
            case 9:
                updateValueByKey();
                FileService.writeFile(data);
                break;
            case 10:
                printRecords(0, data.length);
                break;
        }
    }

    public void checkInput(String input) throws Exception {
        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 10) {
            throw new Exception("No such action.");
        }
    }

}
