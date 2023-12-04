package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    @FXML
    void toChooseFactory(ActionEvent event) {

        Map<String, TabulatedFunctionFactory> map = new HashMap<String, TabulatedFunctionFactory>();
        map.put("Массив", new ArrayTabulatedFunctionFactory());
        map.put("Список", new LinkedListTabulatedFunctionFactory());

        Settings.getInstance().setFactory(map.get(dropDownMenu.getValue()));
        Stage stage = (Stage) dropDownMenu.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Массив", "Список");
        dropDownMenu.getItems().addAll(list);
        dropDownMenu.getSelectionModel().select(0);

        if(Settings.getInstance().getFactory().getClass() == LinkedListTabulatedFunctionFactory.class){
            dropDownMenu.getSelectionModel().select(1);
        }
    }
}
