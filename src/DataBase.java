public interface DataBase {
    void openConnection() throws Exception;

    void closeConnection() throws Exception;

    void checkConnection() throws Exception;

    void readRecordByIndex();

    void isRecordByKey(String key) throws Exception;

    void readRecordByKey();

    Data[] readRecords(String str1, String str2); //Метод может принимать или начальный индекс и количество записей, или начальный и конечный индекс. На выходе массив или коллекция записей.

    void countRecords();

    void addRecord();

    void updateValueByIndex();

    void updateValueByKey();
}
