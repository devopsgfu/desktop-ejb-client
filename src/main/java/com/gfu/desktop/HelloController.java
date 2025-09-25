package com.gfu.desktop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onLoginButtonClick() {
        try {
            new SignIn().getLoginInterface().login(username.getText(), password.getText());
            welcomeText.setText(String.format("Welcome %s, with password %s", username.getText(), password.getText()));
        } catch (Exception e) {
            welcomeText.setText(e.getMessage());
        }
    }
}
