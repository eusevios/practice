package ui;

import functions.TabulatedFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import operations.IntegrationOperator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class IntegrationController implements Initializable {

    public Text integralText;
    public Text integralValueText;
    @FXML
    private Text enterText;

    @FXML
    private TextField textField;

    private TabulatedFunction function;

    @FXML
    void toIntegrate(ActionEvent event) throws ExecutionException, InterruptedException, IOException {

        try {

            int size = Integer.parseInt(textField.getText());
            if (size<2) throw new IllegalArgumentException();
            integralValueText.setText("" + IntegrationOperator.integrate(function, Integer.parseInt(textField.getText())));
            integralText.setVisible(true);
            integralValueText.setVisible(true);
            enterText.setVisible(false);
            textField.setVisible(false);
        }

        catch (NumberFormatException e){

            UIException.showException("Некорректный ввод!");

        }

        catch (IllegalArgumentException e){

            UIException.showException("Количество потоков должно быть >=1!");

        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        integralText.setVisible(false);
        integralValueText.setVisible(false);

    }


    public void setFunction(TabulatedFunction function) {
        this.function = function;
    }
}
