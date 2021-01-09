package tasker;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.VBox;
import javafx.util.Callback;
import tasker.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

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

    @FXML
    private TableColumn<Task, String> createdAtColumn;

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

        taskColumn = new TableColumn<Task, String>("Tasks");
        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Task"));
        createdAtColumn = new TableColumn<>("created at");
        createdAtColumn.setCellValueFactory(cellData-> cellData.getValue().getCreatedAt());
        taskViewTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        taskViewTable.getColumns().setAll(addDeleteButton(), taskColumn, createdAtColumn, addCheckBox());


    }

    public void addTask(){
        String taskname = taskInput.getText().trim();
        if(taskname.isEmpty()) return;
        Task newTask = new Task(taskname);

        app.getObservableTaskList().add(newTask);
        taskInput.clear();
    }

    private TableColumn<Task, Void> addDeleteButton(){
        return addButton("x",(task -> app.getObservableTaskList().remove(task)));
    }

    private TableColumn<Task, Void> addCheckBox(){
        System.out.println("task finished");
        return addButton("v",task ->{
            task.setFinishedProperty(true);
            System.out.println(task.isFinished());
        });

    }

    private TableColumn<Task, Void> addButton(String title, Consumer<Task> func){
        TableColumn<Task, Void> buttonColumn = new TableColumn<>("");

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<TableColumn<Task, Void>, TableCell<Task, Void>>() {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param) {
                final TableCell<Task, Void> cell = new TableCell<Task, Void>() {

                    private final Button btn = new Button(title);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Task task = getTableView().getItems().get(getIndex());
                            System.out.println("selectedTask: " + task);
                            func.accept(task);

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
    buttonColumn.setCellFactory(cellFactory);
    return buttonColumn;
    }

    void setMain(Main app) {
        this.app = app;
        taskViewTable.setItems(app.getObservableTaskList());
    }
}
