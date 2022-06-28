public interface DataBase {
    void openConnection() throws Exception;

    void closeConnection() throws Exception;

    void checkConnection() throws Exception;

    void readRecordByIndex();

    void isRecordByKey(String key) throws Exception;

    void readRecordByKey();

    Data[] readRecords(String str1, String str2);

    void countRecords();

    void addRecord();

    void updateValueByIndex();

    void updateValueByKey();
}
