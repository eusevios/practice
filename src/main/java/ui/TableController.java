package ui;

import functions.ArrayTabulatedFunction;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable, Displayable {

    public Button saveButton;
    public Button loadButton;
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

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/SecondConstructorTabulatedFunction.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();
        SecondConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Создание табулированной функции");
        stage.show();

    }

    public void tableCreation(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/FirstConstructorTabulatedFunction.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();
        FirstConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Создание табулированной функции");
        stage.show();

    }

    public void functionPresentation(TabulatedFunction function) {

        this.function = function;

        table.getItems().clear();

        for (int i = 0; i < function.getCount(); i++) {
            table.getItems().add(new TablePoint(function.getX(i), function.getY(i)));
        }

        table.setVisible(true);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        DoubleStringConverter stringConverter = new DoubleStringConverter();

        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        yColumn.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setY(e.getNewValue()));

        table.setEditable(true);

        table.setVisible(false);

    }

    public void saveFunction(ActionEvent event) throws IOException {

        UIInputOutput.saveBin( function);

    }

    public void loadFunction(ActionEvent event) throws IOException, ClassNotFoundException {

        function = UIInputOutput.loadBin();

        System.out.println(function);

        this.functionPresentation(function);

    }
}
