package ui;

import functions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import java.lang.Class;

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

    private Displayable controller;

    @FXML
    double getFrom(ActionEvent event) {

        return Double.parseDouble(fromTextField.getText());

    }

    @FXML
    double getTo(ActionEvent event) {
        return Double.parseDouble(fromTextField.getText());
    }

    @FXML
    double getSize(ActionEvent event) {

        return Integer.parseInt(sizeText.getText());

    }

    @FXML
    void toCreateFunction(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<?>> classes = new Reflections("functions").getTypesAnnotatedWith(Functions.class);

        Map<String, MathFunction> map = new HashMap<>();
        for(Class<?> curr_class : classes){
            map.put(curr_class.getAnnotation(Functions.class).name(), (MathFunction) curr_class.getConstructor().newInstance());
        }

        TabulatedFunction function = Settings.getInstance().getFactory().
                createWithSecondConstructor((MathFunction) map.get(dropDownMenu.getValue()), Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), Integer.parseInt(sizeTextField.getText()));
        controller.functionPresentation(function);
        System.out.println(function);
        Stage stage = (Stage) creationFunctionButton.getScene().getWindow();
        stage.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Set<Class<?>> classes = new Reflections("functions").getTypesAnnotatedWith(Functions.class);

        ArrayList<String> names_list = new ArrayList<>(classes.size());
        for (int i = 0; i < classes.size(); i++){ names_list.add(i, " ");}
        for(Class<?> curr_class : classes){
            Functions annotation = curr_class.getAnnotation(Functions.class);
            names_list.set(annotation.priority(), annotation.name());
        }
        ObservableList<String> list = FXCollections.observableArrayList(names_list);
        dropDownMenu.getItems().addAll(list);
    }

    public void setMainController(Displayable tableController) {

        this.controller = tableController;

    }
}
