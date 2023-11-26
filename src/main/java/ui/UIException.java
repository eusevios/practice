package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UIException {


    static void showException(Exception e) throws IOException {

        FXMLLoader loader = new FXMLLoader(UIException.class.getClassLoader().getResource("ui/exceptionController.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        Stage stage = new Stage();
        ExceptionController exceptionController = loader.getController();
        exceptionController.setWarningMessage(e);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);

        stage.show();

    }


}
