package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws Exception{
        scene = new Scene(loadFXML("Autentificare"));
        stage.setTitle("Autentificare");

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();


    }


    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
