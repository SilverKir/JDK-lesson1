package src.client;

public interface ClientCommands {
    void disconnectFromServer();
    void showMessage(String message);
    boolean connectToServer(String name);
    boolean sendMessageToServer(String message);
    String getName();
}
