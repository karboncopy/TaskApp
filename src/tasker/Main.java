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

    private ObservableList<Task> observableTaskList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/TaskViewer.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setMain(this);
        primaryStage.setTitle("Tasker App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }

    public ObservableList<Task> getObservableTaskList() {
        return observableTaskList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
