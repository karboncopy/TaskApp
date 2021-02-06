package tasker.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {

    private StringProperty task;
    private final LocalDateTime createdAt;
    private StringProperty date;
    private boolean finished;
    private SimpleBooleanProperty finishedProperty;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public Task(){
        this(null);
    }
    public Task(String task){
        this.task=new SimpleStringProperty(task);
        this.finishedProperty=new SimpleBooleanProperty(false);
        createdAt = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM");
        this.date = new SimpleStringProperty(createdAt.format(dateTimeFormatter));
    }

    public boolean isFinished() {
        return finishedProperty.get();
    }

    public void setFinishedProperty(boolean isFinished) {
        propertyChangeSupport.firePropertyChange("finishedProperty", this.finishedProperty, isFinished);
        setFinished(isFinished);
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
        return this.date;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public String toString() {
        return "Task{" +
                "task=" + task +
                ",\n createdAt=" + createdAt +
                ",\n date=" + date +
                ",\n finished=" + finished +
                ",\n finishedProperty=" + finishedProperty +
                '}';
    }
}
