package local;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;


    @FXML
    void logIn() throws IOException {
        App.setRoot("game");
        App.resize();
    }

    @FXML
    void click() throws IOException {
        App.setRoot("register");
    }
}
