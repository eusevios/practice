package ui;

import functions.ArrayTabulatedFunction;
import functions.Insertable;
import functions.Point;
import functions.TabulatedFunction;
import io.FunctionsIO;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable, Displayable {

    public Button saveButton;
    public Button loadButton;
    public Button changeButton;
    public Button integrateButton;
    @FXML
    private Button functionButton;

    @FXML
    private Pane pane;

    @FXML
    private TableView<TablePoint> table;

    @FXML
    private Button tableButton;

    @FXML
    private TableColumn<TablePoint, Double> xColumn;

    @FXML
    private TableColumn<TablePoint, Double> yColumn;

    TabulatedFunction function;

    public void onFunctionCreation(ActionEvent event) throws IOException {

        FXMLLoader loader = WindowOpener.openWindow("ui/SecondConstructorTabulatedFunction.fxml", "Создание функции", 900, 600);
        SecondConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);

    }

    public void tableCreation(ActionEvent event) throws IOException {


        FXMLLoader loader = WindowOpener.openWindow("ui/FirstConstructorTabulatedFunction.fxml", "Создание функции", 900, 600);
        FirstConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);

    }

    public void functionPresentation(TabulatedFunction function) {

        this.function = function;

        table.getItems().clear();

        for (int i = 0; i < function.getCount(); i++) {
            table.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
        }

        saveButton.setVisible(true);
        changeButton.setVisible(true);
        integrateButton.setVisible(true);


    }

    @Override
    public void addPoint(double x, double y) {

        TablePoint point = new TablePoint(x,y);

        if(function.indexOfX(x)<0) {
            System.out.println(2);
            int k;
            for(k = 0; k<function.getCount(); k++){
                if(function.getX(k)>x) break;
            }
            table.getItems().add(k, point);
        }
        else table.getItems().set(function.indexOfX(x), point);

    }

    @Override
    public void removePoint(double x) {

        table.getItems().remove(function.indexOfX(x));
        if (table.getItems().isEmpty()){
            saveButton.setVisible(false);
            changeButton.setVisible(false);
            integrateButton.setVisible(false);
        }
    }

    @Override
    public TabulatedFunction getFunc() {
        return function;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setPlaceholder(new Label(""));

        saveButton.setVisible(false);
        changeButton.setVisible(false);
        integrateButton.setVisible(false);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        DoubleStringConverter stringConverter = new DoubleStringConverter();

        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        yColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setY(e.getNewValue());
            function.setY(   e.getTablePosition().getRow(), e.getNewValue());
        });

        table.setEditable(true);

    }

    public void saveFunction(ActionEvent event) throws IOException {

        UIInputOutput.save( function);

    }

    public void loadFunction(ActionEvent event) throws IOException, ClassNotFoundException {

        TabulatedFunction func = UIInputOutput.load();

        if(func !=null) {
            function = func;

            this.functionPresentation(function);
        }

    }

    public void changeTable(ActionEvent event) throws IOException {


        FXMLLoader loader = WindowOpener.openWindow("ui/FunctionChange.fxml", "Редактирование", 400, 400);
        FunctionChangeController controller = loader.getController();
        controller.setMainController(this);



    }

    public void toIntegrate(ActionEvent event) throws IOException {

        IntegrationController controller = WindowOpener.openWindow("ui/Integration.fxml", "Интегрирование", 320, 240).getController();
        controller.setFunction(function);

    }

}
