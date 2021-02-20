package tasker.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tasker.model.Task;
import tasker.model.TaskListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlFileProcessor implements FileReaderWriter {


    @Override
    public void writeToFile(String filename, List data) {
        try{
            File file = new File(filename);
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            TaskListWrapper wrapper = new TaskListWrapper();
            wrapper.setTasks(data);
            marshaller.marshal(wrapper, file);
            marshaller.marshal(wrapper, System.out);
        }
        catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not write data");
            alert.setContentText("Could not write data to  file:\n" + filename);

            alert.showAndWait();
        }
    }

    @Override
    public List<Task> readFromFile(String filename) {
        File file = new File(filename);
        List<Task> result;
        try {

            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            TaskListWrapper wrapper = (TaskListWrapper) unmarshaller.unmarshal(file);
            System.out.println(wrapper.toString());

            result = wrapper.getTasks();
        }catch (Exception e){
            result = FXCollections.observableArrayList();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
        return result;
    }
}
