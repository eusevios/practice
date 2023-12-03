package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowOpener {

    static FXMLLoader openWindow(String source, String name, int w, int h) throws IOException {

        FXMLLoader loader = new FXMLLoader(WindowOpener.class.getClassLoader().getResource(source));
        Parent root = loader.load();
        Scene scene = new Scene(root, w, h);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.show();
        return loader;

    }

}
