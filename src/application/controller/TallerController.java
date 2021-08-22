package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TallerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnTest;

    @FXML
    void clickTest(ActionEvent event) {
    	System.out.println("Hola Mundo");
    }

    @FXML
    void initialize() {
        assert btnTest != null : "fx:id=\"btnTest\" was not injected: check your FXML file 'Taller.fxml'.";

    }
}

