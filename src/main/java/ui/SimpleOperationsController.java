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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import operations.TabulatedFunctionOperationService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
    public Button integrationButton;
    public AnchorPane anchorPane;

    @FXML
    Parent firstTable;

    @FXML
    Parent secondTable;
    @FXML
    TableController firstTableController;
    @FXML
    TableController secondTableController;
    @FXML
    public Pane pane;

    TabulatedFunction function;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setPlaceholder(new Label(""));
        saveResultButton.setVisible(false);
        integrationButton.setVisible(false);

        ObservableList<String> list = FXCollections.observableArrayList("+", "-", "*", ":");
        opChoice.getItems().addAll(list);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        opChoice.getSelectionModel().select(0);

    }

    public void doOperation(ActionEvent action) throws IOException {

        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();

        switch (opChoice.getValue()) {

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

        for (int i = 0; i < function.getCount(); i++) {
            table.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
        }

        saveResultButton.setVisible(true);
        integrationButton.setVisible(true);

    }


    public void saveResult(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Bin files", "*.bin"));

        File file = fileChooser.showSaveDialog(null);

        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        FunctionsIO.serialize(bufferedOutputStream, function);

    }

    public void toIntegrate(ActionEvent event) throws IOException {

        IntegrationController controller = WindowOpener.openWindow("ui/Integration.fxml", "Интегрирование", 320, 240).getController();
        controller.setFunction(function);


    }
}
