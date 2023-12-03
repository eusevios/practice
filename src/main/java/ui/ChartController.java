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
    void toLoad(ActionEvent event) throws IOException, ClassNotFoundException {

        function = UIInputOutput.load();
        functionPresentation(function);

    }

    @FXML
    void toSave(ActionEvent event) throws IOException {

        UIInputOutput.save(function);

    }

    @Override
    public void functionPresentation(TabulatedFunction function) {

        saveButton.setVisible(true);
        loadButton.setVisible(true);
        changeButton.setVisible(true);
        integrationButton.setVisible(true);

        chart.getData().clear();

        this.function = function;
        System.out.println(function);

        series = new XYChart.Series<>();

        for(int i = 0; i<function.getCount(); i++){

            series.getData().add(new XYChart.Data<>(function.getX(i), function.getY(i)));

        }
        chart.getData().add(series);

    }

    @Override
    public void addPoint(double x, double y) {

        if(function.indexOfX(x)>=0) {
            System.out.println(3);
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
    public void removePoint(int index) {

        series.getData().removeIf(data -> data.getXValue() == function.getX(index));
        if(series.getData().isEmpty()){
            saveButton.setVisible(false);
            loadButton.setVisible(false);
            changeButton.setVisible(false);
            integrationButton.setVisible(false);
        }

    }

    @Override
    public TabulatedFunction getFunc() {
        return function;
    }

    public void toChange(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/FunctionChange.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        FunctionChangeController controller = loader.getController();
        controller.setMainController(this);
        controller.setFunction(function);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Редактирование");
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saveButton.setVisible(false);
        loadButton.setVisible(false);
        changeButton.setVisible(false);
        integrationButton.setVisible(false);

    }

    public void toIntegrate(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ui/Integration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 320, 240);
        Stage stage = new Stage();
        IntegrationController controller = loader.getController();
        controller.setMainController(this);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Интегрирование");
        stage.show();

    }
}
