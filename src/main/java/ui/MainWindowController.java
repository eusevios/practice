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

import java.io.IOException;

public class MainWindowController {

    public AnchorPane anchorPane;
    public Button functionsOperationsButton;
    public Button diffFunctionButton;
    public Button settingsButton;
    public Button chartButton;
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

    public void settings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/settings.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 335, 300);
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Настройки");
        stage.show();
    }

    public void chart(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/Chart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 900);
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Графики");
        stage.show();

    }
}
