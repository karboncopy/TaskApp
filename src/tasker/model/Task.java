package tasker.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {

    private StringProperty task;
    private final LocalDateTime createdAt;
    private StringProperty date;
    private boolean finished;
    private SimpleBooleanProperty finishedProperty;


    public Task(String task){
        this.task=new SimpleStringProperty(task);
        this.finishedProperty=new SimpleBooleanProperty(true);
        createdAt = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM");
        this.date = new SimpleStringProperty(createdAt.format(dateTimeFormatter));

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

    public StringProperty getCreatedAt(String format){
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
         this.date.set(createdAt.format(dateTimeFormatter));
         return this.date;
    }

    public StringProperty getCreatedAt(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM");
        this.date.set(createdAt.format(dateTimeFormatter));
        return this.date;
    }

}
