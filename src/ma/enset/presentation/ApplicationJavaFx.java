package ma.enset.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("views/main.fxml"));
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style/style.css").toString());
        primaryStage.setTitle("gestion des commandes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
