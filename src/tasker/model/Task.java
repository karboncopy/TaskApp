package tasker.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.io.Serializable;

public class Task implements Serializable {

    private StringProperty task;
    private boolean finished;
    private SimpleBooleanProperty finishedProperty;

    public Task(){}

    public Task(String task){
        this.task=new SimpleStringProperty(task);
        this.finishedProperty=new SimpleBooleanProperty(true);
    }

    public boolean isFinished() {
        return finishedProperty.get();
    }

    public void setFinishedProperty(boolean isFinished) {
       this.finishedProperty.set(isFinished);
    }

    public void setFinished(boolean finished) {
        this.finished=finished;
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

}
