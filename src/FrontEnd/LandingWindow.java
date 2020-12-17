package FrontEnd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



public class LandingWindow {
    SceneChanger sceneChanger = new SceneChanger();
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button signInButton, registerButton;
    public void signInButtonOnAction() throws Exception {
        sceneChanger.sceneChange("FrontEnd/SignInWindow.fxml",anchorPane);

    }
    public void registerButtonOnAction() throws Exception {
        sceneChanger.sceneChange("FrontEnd/RegistrationWindow.fxml",anchorPane);

    }
}
