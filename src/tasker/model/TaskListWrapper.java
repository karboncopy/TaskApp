package tasker.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="tasks")
public class TaskListWrapper {

    private List<Task> tasks;

    @XmlElementWrapper(name="tasklist")
    @XmlElement(name="taskelement")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TaskListWrapper{" +
                "tasks=" + tasks +
                '}';
    }
}
