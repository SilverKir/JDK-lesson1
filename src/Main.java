package src;
import src.client.ClientGUI;
import src.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow= new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);

    }
}
