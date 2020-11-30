package tasker;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tasker.model.Task;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField taskInput;

    @FXML
    private VBox tasksContainerBox;

    @FXML
    private Label taskDisplay;

    @FXML
    public void addTaskfromTextField(KeyEvent event) {
        if((event.getCode()==KeyCode.ENTER)){
            addTask();
        }
    }

    @FXML
    public void addTaskFromButton() {
       addTask();
    }

    public void addTask(){
        try {
            BorderPane line = FXMLLoader.load(getClass().getResource("view/Task.fxml"));
            tasksContainerBox.getChildren().add(line);
            String task = taskInput.getText().trim();
            new Task(task);
            taskDisplay = (Label) line.lookup("#taskDisplay");
            taskDisplay.setText(task);

        }catch (IOException e){
            System.out.println("sorry");
            e.printStackTrace();
        }
        taskInput.clear();
    }
}
