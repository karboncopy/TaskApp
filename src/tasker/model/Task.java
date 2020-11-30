package tasker.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Task implements Serializable {

    private StringProperty task;

    public Task(){}

    public Task(String task){
        this.task=new SimpleStringProperty(task);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

}
