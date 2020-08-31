package lesson_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Controller{

    public BorderPane borderPane;
    public ListView<String> chatPane;

    public Button buttonSend;
    public TextField userText;
    private ObservableList<String> listViewData = FXCollections.observableArrayList();



    public void sendButtonAction(ActionEvent actionEvent) {
        String msg = userText.getText();
        if (!userText.getText().isEmpty()) {
            listViewData.add(msg);
            chatPane.setItems(listViewData);
            userText.clear();
        }
    }
}
