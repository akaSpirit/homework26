public interface DataBase {
    void openConnection();

    void closeConnection();

    boolean checkConnection();

    void readStringByIndex();

    boolean isStringByKey();

    void readStringByKey();

    void readStrings(int num1, int num2); //Метод может принимать или начальный индекс и количество записей, или начальный и конечный индекс. На выходе массив или коллекция записей.

    void countRecords();

    void addRecord();

    void writeStringByIndex();

    void writeStringByKey();
}
