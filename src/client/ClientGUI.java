package src.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 300;
    private JTextArea log;
    private JScrollPane scrollLog;
    private JPanel panelTop, panelBottom;
    private JTextField tfIPAdress, tfPort, tfLogin,tfMessage;
    private  JPasswordField tfPassword;
    private  JButton btnLogin,btnSend;
    private ClientCommands commands;

    public ClientGUI() {
        start();
    }

    private void start() {
        createMainWindow();
        createTopPanel();
        createTextArea();
        createBottomPanel();
        setVisible(true);
    }

    private void createMainWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Client");
    }

    private void createTopPanel() {
        panelTop = new JPanel(new GridLayout(2, 3, 5, 5));
        tfIPAdress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8888");
        tfLogin = new JTextField("ivan");
        tfPassword = new JPasswordField("1234567");
        btnLogin = new JButton("Login");
        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(panelTop, BorderLayout.NORTH);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               loginToServer(tfLogin.getText());
            }
        });
    }

    private void createTextArea() {
        log = new JTextArea();
        log.setEditable(false);
        scrollLog = new JScrollPane(log);
        add(scrollLog);
    }

    private void createBottomPanel(){
        panelBottom = new JPanel(new BorderLayout());
        btnSend = new JButton("Send");
        tfMessage = new JTextField();
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage(tfMessage.getText());
            }
        });

        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    SendMessage(tfMessage.getText());
                }
            }
        });
    }
    private void AddLog(String text) {
        log.append(text + "\n");
    }

    private void SendMessage(String message) {
        if (!message.isEmpty()) {
            if (!commands.sendMessageToServer(message)) {
                disconnectFromServer();
            }
            tfMessage.setText("");
        }
    }
    @Override
    public void showMessage(String message) {
        AddLog(message);
    }

    @Override
    public void disconnectFromServer() {
        showTopPanel();
        log.setText("");
        showMessage("Сервер отключился");
    }

    @Override
    public void setClientController(ClientCommands clientController) {
        this.commands = clientController;
    }

    private void loginToServer(String login){
        if (commands.connectToServer(login)){
            hideTopPanel();
        }
    }

    private void hideTopPanel() {
        panelTop.setVisible(false);
    }
    private void showTopPanel() {
        panelTop.setVisible(true);
    }
}
