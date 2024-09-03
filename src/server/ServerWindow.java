package src.server;

import src.client.ClientGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ServerWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 500;
    public static final int WINDOW_POSY = 100;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    public boolean isServerWorking() {
        return isServerWorking;
    }

    private boolean isServerWorking;

    public List<String> getMessages() {
        return messages;
    }

    public void addClientList(ClientGUI client) {
        if (!this.clientList.contains(client)){
        this.clientList.add(client);}
    }

    private List<ClientGUI> clientList = new ArrayList<>();
    private List<String> messages;

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
        AddLog(currentMessage);
        for (ClientGUI client:clientList
             ) {
            client.AddLog(currentMessage);
        }
        messages.add(currentMessage);
    }

    private String currentMessage;
    public ServerWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat server");
        MessageLog messageLog = new MessageLog();
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    AddLog(ServerConstants.SERVER_RUN);
                } else {
                    isServerWorking = true;
                    log.setText("");
                    AddLog(ServerConstants.SERVER_START);
                    messageLog.DownloadMessageLog();
                    messages=messageLog.getMessageList();
                    PrintMessageLog();
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    AddLog(ServerConstants.SERVER_DOWN);
                } else {
                    isServerWorking = false;
                    AddLog(ServerConstants.SERVER_STOP);
                    messageLog.setMessageList(messages);
                    messageLog.SaveMessageLog();
                }
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        add(panBottom, BorderLayout.SOUTH);
        add(log);
        setVisible(true);
    }

    public void AddLog(String text) {
        log.append(text + "\n");
    }

    public void PrintMessageLog(){
        for (String item: messages
             ) {AddLog(item);
        }
    }
}
