package ui;

import functions.TabulatedFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.TablePoint;

import java.io.IOException;

public class OperationsController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text equalsText;

    @FXML
    private Button firstCreateTableButton;

    @FXML
    private Button firstFunctionCreateButton;

    @FXML
    private TableView<TablePoint> firstTable;

    @FXML
    private TableColumn<TablePoint, Double> firstXColumn;

    @FXML
    private TableColumn<TablePoint, Double> firstYColumn;

    @FXML
    private ChoiceBox<?> operationSelection;

    @FXML
    private Button secondCreationTableButton;

    @FXML
    private Button secondFunctionCreateButton;

    @FXML
    private TableView<TablePoint> secondTable;

    @FXML
    private TableColumn<TablePoint, Double> secondXColumn;

    @FXML
    private TableColumn<TablePoint, Double> secondYColumn;

    @FXML
    private TableView<TablePoint> thirdTable;

    @FXML
    private TableColumn<TablePoint, Double> thirdXColumn;

    @FXML
    private TableColumn<TablePoint, Double> thirdYColumn;

    protected TabulatedFunction firstFunction;

    protected TabulatedFunction secondFunction;

    protected TabulatedFunction buffer;

    @FXML
    void firstCreationTable(ActionEvent event) throws IOException {

        firstSetTable(firstTable);
        System.out.println(2);


    }

    @FXML
    void firstFunctionCreate(ActionEvent event) throws IOException {

        secondSetTable(firstTable);
        System.out.println(2);

    }

    @FXML
    void secondCreationTable(ActionEvent event) {

    }

    @FXML
    void secondFunctionCreate(ActionEvent event) {



    }

    void firstSetTable(TableView table) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/functionCreation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Создание табулированной функции");
        stage.show();
        FunctionCreatingController controller =   loader.getController();
        controller.setMainController(this);

    }

    void secondSetTable(TableView table) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/secondConstructorTabulatedFunction.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Создание табулированной функции");
        stage.show();

    }

}
