package src.client;

import src.server.ServerCommands;

public class ClientController implements ClientCommands {
    private final ServerCommands server;
    private final ClientView view;
    private final ClientCommands client = this;
    private String name;

    public ClientController(ClientView view, ServerCommands server) {
        this.view = view;
        this.server = server;
        view.setClientController(this);
    }

    public boolean connectToServer(String name) {
        if (server.isWorking()) {
            this.name = name;
            if (server.connectToServer(client)) {
                return true;
            }
            view.showMessage("Подключение не удалось. Абонент с таким именем существует!");
            return false;
        } else {
            view.showMessage("Подключение не удалось.");
            return false;
        }
    }

    @Override
    public boolean sendMessageToServer(String message) {
        if (server.isWorking()) {
            server.getMessage(name + " : " + message);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void disconnectFromServer() {
        view.disconnectFromServer();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

}