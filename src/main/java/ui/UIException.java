package ui;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIException {


    static void showException(Exception e){

        Stage stage = new Stage();

        Text text = new Text(e.getMessage());

        Group group = new Group(text);

        Scene scene = new Scene(group);

        stage.setScene(scene);

        stage.show();
    }


}
