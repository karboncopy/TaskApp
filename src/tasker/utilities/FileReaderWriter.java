package tasker.utilities;

import tasker.model.Task;

import java.util.List;

public interface FileReaderWriter {

    public void writeToFile(String filename, List data);
    public List<Task> readFromFile(String filename);
}
