package src.server;

import src.client.ClientCommands;

public interface ServerCommands {
    boolean connectToServer(ClientCommands client);
    boolean isWorking();
    void getMessage(String message);
    void startServer();
    void closeServer();
}
