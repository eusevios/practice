package ui;

import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SecondConstructorTabulatedFunctionController implements Initializable {

    @FXML
    private AnchorPane acnhorPane;

    @FXML
    private ChoiceBox<String> dropDownMenu;

    @FXML
    private Text fromText;

    @FXML
    private TextField fromTextField;

    @FXML
    private Text selectFuncitonText;

    @FXML
    private Text sizeText;

    @FXML
    private TextField sizeTextField;

    @FXML
    private Text toText;

    @FXML
    private TextField toTextField;

    @FXML
    private Button creationFunctionButton;

    TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();


    @FXML
    double getFrom(ActionEvent event) {

        return Double.parseDouble(fromTextField.getText());

    }

    @FXML
    double getTo(ActionEvent event) {
        return  Double.parseDouble(fromTextField.getText());
    }

    @FXML
    double getSize(ActionEvent event) {

        return  Integer.parseInt(fromTextField.getText());

    }

    @FXML
    void toCreateFunction(ActionEvent event) {

        Map<String, MathFunction> map = new HashMap<String, MathFunction>();
        map.put("y=0", new ZeroFunction());
        map.put("y=1", new UnitFunction());
        map.put("y=-1*(x + pi/2)", new AdditionalFunction());
        map.put("Квадратичная функция", new SqrFunction());
        map.put("Натуральный Логарифм", new NaturalLogarithm());
        map.put("Тождественная функция", new IdentityFunction());

        TabulatedFunction function = factory.createWithSecondConstructor(map.get(dropDownMenu.getValue()), Double.parseDouble(fromTextField.getText()),  Double.parseDouble(toTextField.getText()),  Integer.parseInt(sizeTextField.getText()));
        System.out.println(function);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("y=0", "y=1", "y=-1*(x + pi/2)", "Квадратичная функция", "Натуральный Логарифм", "Тождественная функция");
        dropDownMenu.getItems().addAll(list);
    }
}
