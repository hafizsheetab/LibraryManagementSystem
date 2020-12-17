package FrontEnd;
import  APIs.PBE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationWindow {
    @FXML
    TextField reply, name;
    @FXML
    ComboBox userType;
    @FXML
    PasswordField password;
    @FXML
    Button registerButton;
    public void initialize(){
        userType.getItems().addAll("General","Premium","VIP");
        userType.getSelectionModel().select("General");
    }
    public void registerButtonOnAction () throws Exception {
        String name1 = name.getText();
        String password1 = password.getText();
        String userType1 = (String)userType.getValue();
        String reply1 = formValidator(name1, password1, userType1);
//        password1 = PBE.encrypt(password1.toCharArray(),"MySecretToken");
        System.out.println(password1);
        System.out.println(reply1);
        if(!reply1.isEmpty()){
            reply.setText(reply1);
            return;
        }

    }

    private String formValidator(String name1, String password1, String userType1) {
        String reply = new String();
        if(name1.isEmpty()){
            reply = reply + "Name Not Given\n";
        }
        if(password1.length() < 6){
            reply = reply + "Password should be over 6 characters\n";
        }
        return reply;
    }


}
