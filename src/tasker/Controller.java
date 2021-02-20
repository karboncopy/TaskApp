package tasker;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import tasker.model.Task;
import tasker.utilities.ButtonBaseFactory;
import tasker.view.TaskStatusListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class Controller<T> {

    @FXML
    private TextField taskInput;

    @FXML
    private VBox tasksContainerBox;

    @FXML
    private TableView<Task> taskViewTable;

    @FXML
    private TableColumn<Task, T> deleteButtonColumn;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, T> finishedTaskColumn;

    @FXML
    private TableColumn<Task, String> createdAtColumn;

    ButtonBaseFactory baseFactory = new ButtonBaseFactory();

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

        deleteButtonColumn = addDeleteButton();
        taskColumn = new TableColumn<Task, String>("Tasks");
        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("Task"));
        createdAtColumn = new TableColumn<>("created at");
        createdAtColumn.setCellValueFactory(cellData-> cellData.getValue().getCreatedAt("d MMM uuuu"));
        taskViewTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        taskViewTable.getColumns().setAll(deleteButtonColumn, taskColumn, createdAtColumn);

    }

    public void addTask(){
        String taskname = taskInput.getText().trim();
        if(taskname.isEmpty()) return;
        Task newTask = new Task(taskname);

        app.getObservableTaskList().add(newTask);
        taskInput.clear();
    }

    private TableColumn<Task, T> addDeleteButton(){
        return addButton("button",(task -> app.getObservableTaskList().remove(task)));
    }

    private TableColumn<Task, T> addCheckBox(){
        return addButton("checkbox",task ->{
            //TODO toggle finishedProperty
            if(!task.isFinished()) {
                task.setFinishedProperty(true);
            }else{
                task.setFinishedProperty(false);
            }
            task.addPropertyChangeListener("finishedProperty", new TaskStatusListener());
        });
    }

    private <T> TableColumn<Task, T> addButton(String elemType, Consumer<Task> func){
        TableColumn<Task, T> buttonColumn = new TableColumn<>("");

        Callback<TableColumn<Task, T>, TableCell<Task, T>> cellFactory = new Callback<TableColumn<Task, T>, TableCell<Task, T>>() {
            @Override
            public TableCell<Task, T> call(final TableColumn<Task, T> param) {
                final TableCell<Task, T> cell = new TableCell<Task, T>() {
                    private final ButtonBase btn = baseFactory.getButtonBase(elemType);
                    {
                         btn.setOnAction((ActionEvent event) -> {
                             //TODO update table rows correctly to reflect change in Task.finishedProperty

                            Task task = getTableView().getItems().get(getIndex());
                            func.accept(task);
                            System.out.println("selectedTask: " + task.toString());
                        });
                    }

                    @Override
                    public void updateItem(T item, boolean empty) {
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
