package tasker.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.io.Serializable;

public class Task implements Serializable {

    private StringProperty task;
    private ObservableList<Node> control;

    public Task(){}

    public Task(String task){
        this.task=new SimpleStringProperty(task);
    }

    public String getTask() {
        return task.get();
    }

    public StringProperty getTaskProperty(){
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public void setTaskProperty(String taskName){
        this.task.set(taskName);
    }

    public void setControl(ObservableList<Node> control) {
        this.control=control;
    }

    public ObservableList<Node> getControl() {
        return control;
    }
}
