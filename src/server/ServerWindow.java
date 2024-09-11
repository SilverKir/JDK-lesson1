package src.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    public static final int WINDOW_HEIGHT = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 500;
    public static final int WINDOW_POSY = 100;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private ServerCommands server;
    public ServerWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat server");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isWorking()) {
                    AddLog(ServerConstants.SERVER_RUN);
                } else {
                    log.setText("");
                    AddLog(ServerConstants.SERVER_START);
                    server.startServer();
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!server.isWorking()) {
                    AddLog(ServerConstants.SERVER_DOWN);
                } else {
                   server.closeServer();
                    AddLog(ServerConstants.SERVER_STOP);
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

    @Override
    public void showMessage(String message) {
        AddLog(message);
    }

    @Override
    public void setServer(ServerController serverController) {
        this.server=serverController;
    }
}
