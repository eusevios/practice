package ui;

import functions.AbstractTabulatedFunction;
import functions.Insertable;
import functions.Removable;
import functions.TabulatedFunction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FunctionChangeController implements Initializable {

    @FXML
    private ChoiceBox<?> chooseBox;

    @FXML
    private Text indText;

    @FXML
    private TextField indTextField;

    @FXML
    private Button okButton;

    @FXML
    private TextField xField;

    @FXML
    private Text xText;

    @FXML
    private TextField yField;

    @FXML
    private Text yText;

    Displayable mainController;

    TabulatedFunction function;

    public Displayable getMainController() {
        return mainController;
    }

    public void setMainController(Displayable mainController) {
        this.mainController = mainController;
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    public void setFunction(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void toAdd(ActionEvent event) throws IOException {
        try {
            mainController.addPoint(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));

            if (mainController.getFunc().indexOfX(Double.parseDouble(xField.getText())) < 0) {

                ((Insertable) mainController.getFunc()).insert(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
            }

            else {
                mainController.getFunc().setY(mainController.getFunc().indexOfX(Double.parseDouble(xField.getText())),  Double.parseDouble(yField.getText()));
            }
        }
        catch (NumberFormatException e){
            UIException.showException("Некорректный ввод!");
        }
        finally {
            yField.clear();
            xField.clear();
        }
    }

    public void toDelete(ActionEvent event) throws IOException {
        try {
            if (mainController.getFunc().indexOfX(Double.parseDouble(indTextField.getText())) == -1)
                throw new IllegalArgumentException();

            mainController.removePoint(Double.parseDouble(indTextField.getText()));
            ((Removable) mainController.getFunc()).remove(mainController.getFunc().indexOfX(Double.parseDouble(indTextField.getText())));
        }
        catch (NumberFormatException e){
            UIException.showException("Некорректный ввод!");
        }
        catch (IllegalArgumentException e){
            UIException.showException("Точки не существует!");
        }
        finally {
            indTextField.clear();
        }

    }


}
