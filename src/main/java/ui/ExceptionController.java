package ui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ExceptionController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text warningText;

    public void setWarningMessage(Exception e){
        warningText.setText(e.getMessage());
    }

}
