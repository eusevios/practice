package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class MainWindowController {

    public AnchorPane anchorPane;
    public Button functionsOperationsButton;
    @FXML
    private Button functionCreating;

    @FXML
    private Text textLab;

    public void functionsOperations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/SimpleOperations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Операции над табулированными функциями");
        stage.show();
    }

    public void diffFunction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/DiffFunction.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Дифференцирование табулированных функций");
        stage.show();

    }
}