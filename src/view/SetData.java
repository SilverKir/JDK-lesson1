package src.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SetData {
    public static void setData(List<String> messageList, String path) {
        if (!messageList.isEmpty()) {
            try (FileWriter writer = new FileWriter(path, false)){
                for (String item: messageList) {
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
