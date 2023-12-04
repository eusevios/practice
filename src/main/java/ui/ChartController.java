package ui;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Displayable, Initializable {

    public NumberAxis yAxis;
    public NumberAxis xAxis;
    public TextField xTextField;
    public Text yText;
    public Button changeButton;
    public Button integrationButton;
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

    XYChart.Series<Double, Double> series;

    TabulatedFunction function;

    @FXML
    void createOnFunction(ActionEvent event) throws IOException {

        FXMLLoader loader = WindowOpener.openWindow("ui/SecondConstructorTabulatedFunction.fxml", "Создание функции", 900, 600);
        SecondConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);

    }

    @FXML
    void createOnTable(ActionEvent event) throws IOException {

        FXMLLoader loader = WindowOpener.openWindow("ui/FirstConstructorTabulatedFunction.fxml", "Создание функции", 900, 600);
        FirstConstructorTabulatedFunctionController controller = loader.getController();
        controller.setMainController(this);

    }

    @FXML
    void toLoad(ActionEvent event) throws IOException, ClassNotFoundException {

        TabulatedFunction func = UIInputOutput.load();

        if(func !=null) {
            function = func;

            this.functionPresentation(function);
        }
    }

    @FXML
    void toSave(ActionEvent event) throws IOException {

        UIInputOutput.save(function);

    }

    @Override
    public void functionPresentation(TabulatedFunction function) {

        saveButton.setVisible(true);
        changeButton.setVisible(true);
        integrationButton.setVisible(true);

        chart.getData().clear();

        this.function = function;

        series = new XYChart.Series<>();

        for(int i = 0; i<function.getCount(); i++){

            series.getData().add(new XYChart.Data<>(function.getX(i), function.getY(i)));

        }
        chart.getData().add(series);

    }

    @Override
    public void addPoint(double x, double y) {

        if(function.indexOfX(x)>=0) {
            for (XYChart.Data<Double, Double> data : series.getData()) {
                if (data.getXValue() == x) {
                    data.setYValue(y);
                    break;
                }
            }
        }
        else {
            series.getData().add(new XYChart.Data<>(x, y));
        }
    }

    @Override
    public void removePoint(double x) {

        series.getData().removeIf(data -> data.getXValue() == x);

        if(series.getData().isEmpty()){
            saveButton.setVisible(false);
            changeButton.setVisible(false);
            integrationButton.setVisible(false);
        }

    }

    @Override
    public TabulatedFunction getFunc() {
        return function;
    }

    public void toChange(ActionEvent event) throws IOException {

        FXMLLoader loader = WindowOpener.openWindow("ui/FunctionChange.fxml", "Редактирование", 400, 400);
        FunctionChangeController controller = loader.getController();
        controller.setMainController(this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setLabel("Ось X");
        yAxis.setLabel("Ось Y");

        saveButton.setVisible(false);
        changeButton.setVisible(false);
        integrationButton.setVisible(false);

    }

    public void toIntegrate(ActionEvent event) throws IOException {

        IntegrationController controller = WindowOpener.openWindow("ui/Integration.fxml", "Интегрирование", 320, 240).getController();
        controller.setFunction(function);

    }
}
