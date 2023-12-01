package ui;

import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

public class ChartController implements Displayable {

    public NumberAxis yAxis;
    public NumberAxis xAxis;
    @FXML
    private LineChart<Double, Double> chart;

    @FXML
    private Button createOnFunctionButton;

    @FXML
    private Button createOnTableButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    TabulatedFunction function;

    @FXML
    void createOnFunction(ActionEvent event) throws IOException {

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

    @FXML
    void createOnTable(ActionEvent event) throws IOException {

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

    @FXML
    void toLoad(ActionEvent event) {

    }

    @FXML
    void toSave(ActionEvent event) {

    }

    @Override
    public void functionPresentation(TabulatedFunction function) {

        this.function = function;
        System.out.println(function);

        XYChart.Series<Double, Double> series = new XYChart.Series<>();

        for(int i = 0; i<function.getCount(); i++){

            series.getData().add(new XYChart.Data<>(function.getX(i), function.getY(i)));

        }
        chart.getData().add(series);

    }
}
