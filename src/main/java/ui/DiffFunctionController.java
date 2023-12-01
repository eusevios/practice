package ui;

import functions.TabulatedFunction;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import operations.TabulatedDifferentialOperator;

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

    @FXML
    TableView<TablePoint> diffFuncTable;

    TabulatedFunction function;


    public void diffFunction(ActionEvent event) {

        TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator();

        function = oper.derive(funcTableController.function);

        diffFuncTable.getItems().clear();

        for (int i = 0; i < function.getCount(); i++) {
            diffFuncTable.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        diffFuncTable.setFixedCellSize(30);
        diffFuncTable.prefHeightProperty().bind(diffFuncTable.fixedCellSizeProperty().multiply(Bindings.size(diffFuncTable.getItems()).add(1.01)));

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

    }
}
