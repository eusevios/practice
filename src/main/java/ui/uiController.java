package ui;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class uiController implements Initializable {

    @FXML
    private Text EnterSize;

    @FXML
    private TableView<TablePoint> Table;

    @FXML
    private TextField textF;

    @FXML
    private TableColumn<TablePoint, Double> xColumn;

    @FXML
    private TableColumn<TablePoint, Double> yColumn;



    @FXML
    void TextEnter(ActionEvent event) {

        int size = Integer.parseInt(textF.getText());
        for(int i = 0; i<size; i++){
            Table.getItems().add(new TablePoint());
        }
        editableCols();
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        textF.setVisible(false);
        EnterSize.setVisible(false);
        Table.setVisible(true);



    }

    private void editableCols(){
        DoubleStringConverter stringConverter = new DoubleStringConverter();

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        xColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setX(e.getNewValue()));

        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        yColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setY(e.getNewValue()));

        Table.setEditable(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Table.setVisible(false);

    }

    @FXML
    void creatingFunction(){

        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        double[] xValues = new double[Table.getItems().size()];
        double[] yValues = new double[Table.getItems().size()];

        for(int i = 0; i<Table.getItems().size(); i++){
            xValues[i] = Table.getItems().get(i).x;
            yValues[i] = Table.getItems().get(i).y;
        }

        TabulatedFunction func = factory.create(xValues, yValues);

        System.out.println(func);

    }

}
