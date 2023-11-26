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

public class mainWindowController {

    public AnchorPane anchorPane;
    @FXML
    private Button functionCreating;

    @FXML
    private Text textLab;

    @FXML
    void toCreateFunction(ActionEvent event) throws IOException {

        File f = new File("C:\\Users\\Иван\\IdeaProjects\\practice\\src\\main\\resources\\functionCreation.fxml");
        URL url = f.toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 300, 300);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Создание табулированной функции");
        stage.show();


    }

    @FXML
    void toCreateFunctionWithSecondConstructor() throws IOException {

        File f = new File("C:\\Users\\Иван\\IdeaProjects\\practice\\src\\main\\java\\ui\\secondConstructorTabulatedFunction.fxml");
        URL url = f.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Создание табулированной функции из другой функции");
        stage.show();

    }

}
