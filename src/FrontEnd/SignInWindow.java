package FrontEnd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignInWindow {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button submit;
    @FXML
    TextField id, reply;
    @FXML
    PasswordField password;

    public void submitButtonOnAction() throws Exception {
//        int id1 = Integer.parseInt(id.getText());
//        String password1 = password.getText();
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.sceneChange("ProfileWindow.fxml",anchorPane);
    }
}
