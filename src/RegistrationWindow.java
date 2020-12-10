import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationWindow {
    @FXML
    ComboBox userType;
    public void initialize(){
        userType.getItems().addAll("General","Premium","VIP");
        userType.getSelectionModel().select("General");
    }
}
