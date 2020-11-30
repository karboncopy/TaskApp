package tasker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tasker.model.Task;

public class Main extends Application {

    private ObservableList<Task> taskObservableList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/TaskViewer.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tasker App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        Controller controller = loader.getController();
        controller.setMain(this);
    }

    public ObservableList<Task> getTaskObservableList() {
        return taskObservableList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
