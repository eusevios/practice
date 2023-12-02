package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import operations.IntegrationOperator;

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

    private Displayable mainController;

    @FXML
    void toIntegrate(ActionEvent event) throws ExecutionException, InterruptedException {

        integralValueText.setText(""+IntegrationOperator.integrate(mainController.getFunc(), Integer.parseInt(textField.getText())));

        integralText.setVisible(true);
        integralValueText.setVisible(true);
        enterText.setVisible(false);
        textField.setVisible(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        integralText.setVisible(false);
        integralValueText.setVisible(false);

    }


    public void setMainController(Displayable tableController) {
        mainController = tableController;
    }
}
