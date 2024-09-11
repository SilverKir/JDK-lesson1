package src;
import src.client.ClientController;
import src.client.ClientGUI;
import src.server.ServerCommands;
import src.server.ServerController;
import src.server.ServerWindow;
import src.storage.Archive;

public class Main {
    public static void main(String[] args) {
        ServerCommands serverController = new ServerController( new ServerWindow(), new Archive());
        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);

    }
}
