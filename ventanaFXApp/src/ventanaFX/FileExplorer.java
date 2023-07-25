package ventanaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileExplorer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventana2.fxml"));
        Parent root = loader.load();
        FileExplorerController controller = loader.getController();

        primaryStage.setTitle("File Explorer");
        primaryStage.setScene(new Scene(root, 704,  574));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
