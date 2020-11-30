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

    private Main app;

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
        String task = taskInput.getText().trim();
        if(task.isEmpty()) return;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("view/Task.fxml"));
            BorderPane line = loader.load();
            tasksContainerBox.getChildren().add(line);

            app.getTaskObservableList().add(new Task(task));
            Label taskDisplay = (Label) line.lookup("#taskDisplay");
            taskDisplay.setText(task);
        }catch (IOException e){
            System.out.println("sorry");
            e.printStackTrace();
        }
        taskInput.clear();
    }

    void setMain(Main app) {
        this.app = app;
    }
}
