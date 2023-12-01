package ui;

import functions.TabulatedFunction;
import io.FunctionsIO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import operations.TabulatedFunctionOperationService;
import ui.TablePoint;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimpleOperationsController implements Initializable {

    @FXML
    public ChoiceBox<String> opChoice;
    @FXML
    public TableColumn<TablePoint, Double> yColumn;
    @FXML
    public TableColumn<TablePoint, Double> xColumn;
    @FXML
    public TableView<TablePoint> table;
    public Button saveResultButton;

    @FXML Parent firstTable;

    @FXML Parent secondTable;
    @FXML TableController firstTableController;
    @FXML TableController secondTableController;
    @FXML
    public Pane pane;

    TabulatedFunction function;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("+","-","*",":");
        opChoice.getItems().addAll(list);

            table.setFixedCellSize(30);
            table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));

            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

    }

    public void doOperation(ActionEvent action) throws IOException {

        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();

        switch (opChoice.getValue()){

            case "+":
                function = operationService.toAdd(firstTableController.function, secondTableController.function);
                break;

            case "-":
                function = operationService.toSubstract(firstTableController.function, secondTableController.function);
                break;

            case "*":
                function = operationService.toMultiply(firstTableController.function, secondTableController.function);
                break;

            case ":":
                function = operationService.toDivide(firstTableController.function, secondTableController.function);
                break;

        }

        table.getItems().clear();

        for(int i = 0; i<function.getCount(); i++){
            table.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
        }

    }


    public void saveResult(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Bin files","*.bin"));

        File file = fileChooser.showSaveDialog(null);

        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        FunctionsIO.serialize(bufferedOutputStream, function);

    }
}