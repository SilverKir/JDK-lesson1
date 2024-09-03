package src.client;

import src.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientGUI extends JFrame {
    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3, 5, 5));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8888");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("1234567");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private ServerWindow currentServer;
    private ClientGUI clientGUI = this;

    public ClientGUI(ServerWindow server) {
        currentServer = server;
        setLocationRelativeTo(server);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentServer.isServerWorking()) {
                    panelTop.setVisible(false);
                    currentServer.addClientList(clientGUI);
                    AddLog("------ Архив сообщений-------");
                    PrintMessageLog();
                    AddLog("------ Архив сообщений-------");
                    AddLog("Вы успешно подключились!");
                    currentServer.AddLog(tfLogin.getText() + " подключился к беседе");

                } else {
                    AddLog("Подключение не удалось.");
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage();
            }
        });

        tfMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SendMessage();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SendMessage();
                }
            }
        });

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);
        setVisible(true);

    }

    public void AddLog(String text) {
        log.append(text + "\n");
    }

    private void PrintMessageLog() {
        for (String item : currentServer.getMessages()
        ) {
            AddLog(item);
        }
    }

    private void SendMessage() {
        if (currentServer.isServerWorking()) {
            if (tfMessage.getText().length() > 0) {
                currentServer.setCurrentMessage(tfLogin.getText() + ": " + tfMessage.getText());
                tfMessage.setText("");
            }
        } else {
            log.setText("");
            AddLog("Сервер не подключен.");
            panelTop.setVisible(true);
        }
    }
}
