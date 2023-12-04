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

import java.io.IOException;
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
    void getFrom(ActionEvent event) {

        Double.parseDouble(fromTextField.getText());

    }

    @FXML
    void getTo(ActionEvent event) {
         Double.parseDouble(fromTextField.getText());
    }

    @FXML
    void getSize(ActionEvent event) throws IOException {

        try {
            int size = Integer.parseInt(sizeTextField.getText());
            if (size<2) throw new NumberFormatException();
        }
        catch (NumberFormatException e){
            UIException.showException("Некорректный ввод!");
            sizeTextField.clear();
        }
    }

    @FXML
    void toCreateFunction(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Set<Class<?>> classes = new Reflections("functions").getTypesAnnotatedWith(Functions.class);

        Map<String, MathFunction> map = new HashMap<>();
        for(Class<?> curr_class : classes){
            map.put(curr_class.getAnnotation(Functions.class).name(), (MathFunction) curr_class.getConstructor().newInstance());
        }

        try {
            TabulatedFunction function = Settings.getInstance().getFactory().
                    createWithSecondConstructor(map.get(dropDownMenu.getValue()), Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), Integer.parseInt(sizeTextField.getText()));
            controller.functionPresentation(function);
            Stage stage = (Stage) creationFunctionButton.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            UIException.showException("Некорректный ввод!");
        }

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
        dropDownMenu.getSelectionModel().select(0);

    }

    public void setMainController(Displayable tableController) {

        this.controller = tableController;

    }
}
