package tasker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tasker.model.Task;
import tasker.model.TaskListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main extends Application {

    private ObservableList<Task> observableTaskList = FXCollections.observableArrayList(
            new Task("test"),
            new Task("test 2"),
            new Task("test 3")
    );

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/TaskViewer.fxml"));
        Parent root = loader.load();
        loadTaskListFromFile("tasks.xml");
        Controller controller = loader.getController();
        controller.setMain(this);
        primaryStage.setTitle("Tasker App");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/tasker/view/style.css");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
                saveTaskListToFile("tasks.xml");
        });
        primaryStage.show();



    }

    public ObservableList<Task> getObservableTaskList() {
        return observableTaskList;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadTaskListFromFile(String filename){
            File file = new File(filename);;
        try {

            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            TaskListWrapper wrapper = (TaskListWrapper) unmarshaller.unmarshal(file);
            observableTaskList.clear();
            observableTaskList.addAll(wrapper.getTasks());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }


    }

    public void saveTaskListToFile(String filename){
        try{
            File file = new File(filename);
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            TaskListWrapper wrapper = new TaskListWrapper();
            wrapper.setTasks(observableTaskList);
            marshaller.marshal(wrapper, file);
            marshaller.marshal(wrapper, System.out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
