package src.server;

import src.client.ClientCommands;
import src.storage.Archive;
import java.util.ArrayList;
import java.util.List;

public class ServerController implements ServerCommands {
    private boolean isServerWorking;
    private ServerView view;
    private List<ClientCommands> clientList = new ArrayList<>();
    private Archive archive;
    private  String path = ServerConstants.LOG_PATH;
    public ServerController(ServerView view, Archive archive) {
        this.view = view;
        this.archive = archive;
        view.setServer(this);
    }

    @Override
    public boolean connectToServer(ClientCommands client) {
        if (!this.clientList.contains(client)) {
            this.clientList.add(client);
            String message = client.getName() + " подключился к серверу";
            archive.getMessageList().forEach(client::showMessage);
            client.showMessage(message);
            view.showMessage(message);
            return true;
        }
        return false;
    }

    @Override
    public boolean isWorking() {
        return isServerWorking;
    }

    @Override
    public void getMessage(String message) {
            view.showMessage(message);
            sendMessageForClients(message);
            archive.setMessage(message);
    }

    @Override
    public void startServer() {
        isServerWorking = true;
        archive.restoreData(path);
        sendArchiveToServer();
    }

    @Override
    public void closeServer() {
        isServerWorking = false;
        archive.saveData(path);
        disconnectClientsFromServer();
    }

    private void disconnectClientsFromServer (){
        for (ClientCommands client: clientList
             ) {
            client.disconnectFromServer();
        }
        clientList.clear();
    }

    private void sendMessageForClients(String message){
        for (ClientCommands client: clientList
        ) {
            client.showMessage(message);
        }
    }

    private void sendArchiveToServer(){
        archive.getMessageList().forEach(message-> view.showMessage(message));
    }

}
