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
import tasker.utilities.FileReaderWriter;
import tasker.utilities.XmlFileProcessor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main extends Application {

    private ObservableList<Task> observableTaskList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/TaskViewer.fxml"));
        Parent root = loader.load();
        FileReaderWriter fileProcessor = new XmlFileProcessor();
        List<Task> listFromFile = fileProcessor.readFromFile("tasks.xml");
        observableTaskList.addAll(listFromFile);
        Controller controller = loader.getController();
        controller.setMain(this);
        primaryStage.setTitle("Tasker App");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/tasker/view/style.css");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            fileProcessor.writeToFile("tasks.xml", observableTaskList);
        });
        primaryStage.show();
    }

    public ObservableList<Task> getObservableTaskList() {
        return observableTaskList;
    }

    public static void main(String[] args) {
        launch(args);
    }



}
