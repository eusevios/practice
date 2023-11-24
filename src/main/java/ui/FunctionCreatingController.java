package ui;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionCreatingController implements Initializable {

    @FXML
    private Text enterSize;

    @FXML
    private TableView<TablePoint> table;

    @FXML
    private TextField textF;

    @FXML
    private TableColumn<TablePoint, Double> xColumn;

    @FXML
    private TableColumn<TablePoint, Double> yColumn;

    @FXML
    private Button creationButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    @FXML
    void TextEnter(ActionEvent event) {

        int size = Integer.parseInt(textF.getText());

        if (size<0) UIException.showException(new ArithmeticException());

        for(int i = 0; i<size; i++){
            table.getItems().add(new TablePoint());
        }

        textF.setVisible(false);
        enterSize.setVisible(false);

        table.setVisible(true);
        creationButton.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setFixedCellSize(30);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        DoubleStringConverter stringConverter = new DoubleStringConverter();

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        xColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setX(e.getNewValue()));

        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        yColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setY(e.getNewValue()));

        table.setEditable(true);

        table.setVisible(false);
        creationButton.setVisible(false);


    }

    @FXML
    void creatingFunction(){

        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        double[] xValues = new double[table.getItems().size()];
        double[] yValues = new double[table.getItems().size()];

        for(int i = 0; i<table.getItems().size(); i++){
            xValues[i] = table.getItems().get(i).x;
            yValues[i] = table.getItems().get(i).y;
        }

        TabulatedFunction func = factory.create(xValues, yValues);

        System.out.println(func);

    }

}
