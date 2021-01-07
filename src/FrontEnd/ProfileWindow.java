package FrontEnd;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ProfileWindow {
    @FXML
    AnchorPane anchorPane;
    public void logOutOnAction() throws Exception {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.sceneChange("LandingWindow.fxml",anchorPane);
    }
}
