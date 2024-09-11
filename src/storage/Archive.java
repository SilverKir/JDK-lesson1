package src.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archive implements DataStorage {
    private List<String> messageList;

    @Override
    public void setMessage(String message) {
        messageList.add(message);
    }

    @Override
    public List<String> getMessageList() {
        return messageList;
    }

    @Override
    public void restoreData(String path) {
        List<String> data = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Path not found");
            e.printStackTrace();
        }
        messageList = data;
    }

    @Override
    public void saveData(String path) {
        if (!messageList.isEmpty()) {
            try (FileWriter writer = new FileWriter(path, false)) {
                for (String item : messageList) {
                    writer.write(item);
                    writer.append('\n');
                }
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
