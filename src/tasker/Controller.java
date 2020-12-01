package tasker;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import tasker.model.Task;

import java.io.IOException;
import java.util.ListIterator;

public class Controller {

    @FXML
    private TextField taskInput;

    @FXML
    private VBox tasksContainerBox;
    private Label taskDisplay;
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
            loader.setController(this);
            BorderPane line = loader.load();
            tasksContainerBox.getChildren().add(line);

            Task newTask = new Task(task);
            newTask.setControl(line.getChildren());
            app.getObservableTaskList().add(newTask);
            taskDisplay = (Label) line.lookup("#taskDisplay");
            taskDisplay.setText(task);

        }catch (IOException e){
            System.out.println("sorry");
            e.printStackTrace();
        }
        taskInput.clear();
    }

    public void deleteTask(Event event){

        ListIterator<Task> taskListIterator = app.getObservableTaskList().listIterator();
        while(taskListIterator.hasNext()){
            if(taskListIterator.next().getControl().contains(event.getSource())){
                taskListIterator.remove();
            };
        }
    }

    void setMain(Main app) {
        this.app = app;
    }
}
