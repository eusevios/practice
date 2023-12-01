package ui;

import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private AnchorPane acnhorPane;
    @FXML
    private ChoiceBox<String> dropDownMenu;
    TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
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

    @FXML
    void toChooseFactory(ActionEvent event) {

        Map<String, TabulatedFunctionFactory> map = new HashMap<String, TabulatedFunctionFactory>();
        map.put("Массив", new ArrayTabulatedFunctionFactory());
        map.put("Список", new LinkedListTabulatedFunctionFactory());

        factory = map.get(dropDownMenu.getValue());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Массив", "Список");
        dropDownMenu.getItems().addAll(list);
    }
}
