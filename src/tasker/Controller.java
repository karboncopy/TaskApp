package tasker;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.VBox;
import tasker.model.Task;


import java.util.ListIterator;

public class Controller {

    @FXML
    private TextField taskInput;

    @FXML
    private VBox tasksContainerBox;

    @FXML
    private TableView<Task> taskViewTable;

    @FXML
    private TableColumn<Task, String> deleteButtonColumn;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn finishedTaskColumn;

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

    public Controller() {
    }

    @FXML
    private void initialize(){
        TableColumn deleteButtonColumn = new TableColumn("");
        //deleteButtonColumn.setCellValueFactory();
        taskColumn = new TableColumn<Task, String>("Tasks");
        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Task"));
        taskViewTable.getColumns().setAll(taskColumn);
    }

    public void addTask(){
        String taskname = taskInput.getText().trim();
        if(taskname.isEmpty()) return;
        Task newTask = new Task(taskname);
        app.getObservableTaskList().add(newTask);
        //taskViewTable.getItems().add(newTask);
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
        taskViewTable.setItems(app.getObservableTaskList());
    }
}
