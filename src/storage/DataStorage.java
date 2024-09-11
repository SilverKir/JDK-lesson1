package src.storage;

import java.util.List;

public interface DataStorage {
    void restoreData(String path);
    void saveData(String path);
    void setMessage(String message);
    List<String> getMessageList();
}