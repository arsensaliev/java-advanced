package lesson_7.client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lesson_7.server.MyServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public VBox msgPanel;
    @FXML
    public BorderPane borderPane;
    @FXML
    public ListView<String> chatPane;
    @FXML
    public Button buttonSend;
    @FXML
    public TextField userText;
    @FXML
    public VBox authPanel;
    @FXML
    private final ObservableList<String> listViewData = FXCollections.observableArrayList();
    public TextField userLogin;
    public PasswordField userPassword;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthenticated(false);
    }

    private void connect() {
        try {
            String IP_ADDRESS = "localhost";
            int PORT = 8585;
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {

                    while (true) {
                        String strFromServer = in.readUTF();

                        if (strFromServer.startsWith("/authok")) {
                            nickname = strFromServer.split(" ")[1];
                            System.out.println(nickname);
                            setAuthenticated(true);
                            break;
                        }

                        Platform.runLater(() -> {
                            addMessage(strFromServer);
                        });
                    }

                    while (true) {
                        String strFromServer = in.readUTF();

                        if (strFromServer.equals("/end")) {
                            break;
                        }

                        Platform.runLater(() -> {
                            addMessage(strFromServer);
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Мы отключились от сервера");
                    try {
                        socket.close();
                        setAuthenticated(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendButtonAction(ActionEvent actionEvent) {
        try {
            out.writeUTF(userText.getText());
            userText.clear();
            userText.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String msg) {
        listViewData.add(msg);
        chatPane.setItems(listViewData);
    }

    public void signIn(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF(String.format("/auth %s %s", userLogin.getText().trim().toLowerCase(), userPassword.getText()));
            userPassword.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String nickname;

    public void setAuthenticated(boolean authenticated) {
        msgPanel.setVisible(authenticated);
        msgPanel.setManaged(authenticated);
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);

        if (!authenticated) {
            nickname = "";
        }
    }
}
