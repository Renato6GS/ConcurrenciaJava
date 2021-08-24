package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TallerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView workSpace3;

    @FXML
    private ImageView workSpace2;

    @FXML
    private ImageView workSpace1;

    @FXML
    private ImageView mecanico3;

    @FXML
    private ImageView mecanico2;

    @FXML
    private ImageView mecanico1;

    @FXML
    private ImageView clienteEsperando4;

    @FXML
    private ImageView clienteEsperando3;

    @FXML
    private ImageView clienteEsperando2;

    @FXML
    private ImageView clienteEsperando1;

    @FXML
    private ImageView clienteAtendido3;

    @FXML
    private ImageView clienteAtendido2;

    @FXML
    private ImageView clienteAtendido1;

    @FXML
    private Button btnAgregar;

    @FXML
    private Label labelClientesEnEspera;

    @FXML
    private Label labelEstadoMecanico3;

    @FXML
    private Label labelEstadoMecanico2;

    @FXML
    private Label labelEstadoMecanico1;

    @FXML
    private ImageView autoListo1;

    @FXML
    private ImageView autoListo2;

    @FXML
    private ImageView autoListo3;

    @FXML
    void clickBtnAgregar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert workSpace3 != null : "fx:id=\"workSpace3\" was not injected: check your FXML file 'Taller.fxml'.";
        assert workSpace2 != null : "fx:id=\"workSpace2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert workSpace1 != null : "fx:id=\"workSpace1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert mecanico3 != null : "fx:id=\"mecanico3\" was not injected: check your FXML file 'Taller.fxml'.";
        assert mecanico2 != null : "fx:id=\"mecanico2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert mecanico1 != null : "fx:id=\"mecanico1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteEsperando4 != null : "fx:id=\"clienteEsperando4\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteEsperando3 != null : "fx:id=\"clienteEsperando3\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteEsperando2 != null : "fx:id=\"clienteEsperando2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteEsperando1 != null : "fx:id=\"clienteEsperando1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteAtendido3 != null : "fx:id=\"clienteAtendido3\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteAtendido2 != null : "fx:id=\"clienteAtendido2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert clienteAtendido1 != null : "fx:id=\"clienteAtendido1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'Taller.fxml'.";
        assert labelClientesEnEspera != null : "fx:id=\"labelClientesEnEspera\" was not injected: check your FXML file 'Taller.fxml'.";
        assert labelEstadoMecanico3 != null : "fx:id=\"labelEstadoMecanico3\" was not injected: check your FXML file 'Taller.fxml'.";
        assert labelEstadoMecanico2 != null : "fx:id=\"labelEstadoMecanico2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert labelEstadoMecanico1 != null : "fx:id=\"labelEstadoMecanico1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert autoListo1 != null : "fx:id=\"autoListo1\" was not injected: check your FXML file 'Taller.fxml'.";
        assert autoListo2 != null : "fx:id=\"autoListo2\" was not injected: check your FXML file 'Taller.fxml'.";
        assert autoListo3 != null : "fx:id=\"autoListo3\" was not injected: check your FXML file 'Taller.fxml'.";

    }
}
