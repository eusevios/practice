package ui;

import functions.TabulatedFunction;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import operations.TabulatedDifferentialOperator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DiffFunctionController implements Initializable {


    @FXML
    public Pane funcTable;

    @FXML
    public TableController funcTableController;
    public Button toDiff;
    public TableColumn<TablePoint, Double> xColumn;
    public TableColumn<TablePoint, Double> yColumn;
    public Button saveButton;
    public Button integrationButton;

    @FXML
    TableView<TablePoint> diffFuncTable;

    TabulatedFunction function;


    public void diffFunction(ActionEvent event) throws IOException {

        TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator();

        try {

            function = oper.derive(funcTableController.function);

            diffFuncTable.getItems().clear();

            for (int i = 0; i < function.getCount(); i++) {
                diffFuncTable.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
            }

            saveButton.setVisible(true);
            integrationButton.setVisible(true);
        }
        catch (NullPointerException e){
            UIException.showException("Заполните таблицу!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diffFuncTable.setPlaceholder(new Label());

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        saveButton.setVisible(false);
        integrationButton.setVisible(false);

    }

    public void save(ActionEvent event) throws IOException {

        UIInputOutput.save(function);

    }

    public void toIntegrate(ActionEvent event) throws IOException {

        IntegrationController controller = WindowOpener.openWindow("ui/Integration.fxml", "Интегрирование", 320, 240).getController();
        controller.setFunction(function);
    }
}
