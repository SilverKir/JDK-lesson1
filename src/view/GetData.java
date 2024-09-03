package src.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetData {
    public static List<String> getData(String path) {
        List<String> data=new ArrayList<>();
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
        return data;
    }
}
