package View;

import Model.Utilizator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import presenter.PPersistentaUtilizator;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIPersistentaUtilizator implements Initializable, IPersistentaUtilizator {

    @FXML
    protected TextField numeUt;
    @FXML
    protected TextField contUt;
    @FXML
    protected PasswordField passUt;
    @FXML
    protected TextField numeNouUt;
    @FXML
    protected TextField contNouUt;
    @FXML
    protected PasswordField passNouUt;
    @FXML
    protected ComboBox bibliotecaActualizare;
    @FXML
    protected Label mAdauga;
    @FXML
    protected Label mActualizare;
    @FXML
    protected Button stergeUt;
    @FXML
    protected Button adaugaUt;
    @FXML
    protected Button actualizareUt;
    @FXML
    protected Button inapoiPersistentaUt;
    @FXML
    protected Button veziUtilizatori;

    protected String bibliotecaUtilizator;
    protected String rol;
    protected String rolUtilizator;

    public void setB(String text){
        bibliotecaUtilizator = text;
    }
    public void setR(String text){ rol = text; }
    public void setUtilizator(String text){rolUtilizator= text; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bibliotecaActualizare.getItems().removeAll(bibliotecaActualizare.getItems());
        bibliotecaActualizare.getItems().addAll("Daicoviciu", "Observator", "Mecanica", "Marasti");
    }

    @Override
    public String getNume() {
        return numeUt.getText();
    }

    @Override
    public String getCont() {
        return contUt.getText();
    }

    @Override
    public String getPass() {
        return passUt.getText();
    }

    @Override
    public String getNumeNou() {
        return numeNouUt.getText();
    }

    @Override
    public String getContNou() {
        return contNouUt.getText();
    }

    @Override
    public String getPassNou() {
        return passNouUt.getText();
    }

    @Override
    public void setActualizare(String text) {
        mActualizare.setText(text);
    }

    @Override
    public void setAdaugare(String text) {
        mAdauga.setText(text);
    }

    @Override
    public String getBibloteca() {
        return bibliotecaUtilizator;
    }

    @Override
    public String getRolAdauga() {
        return rol;
    }

    @Override
    public String getBibliotecaActualizare() {

        if (bibliotecaActualizare.getSelectionModel().isEmpty() == true)
            return "";

        return bibliotecaActualizare.getValue().toString();
    }

    @Override
    public String getRolUtilizator() {
        return rolUtilizator;
    }

    @Override
    public Button inapoiButton() {
        return inapoiPersistentaUt;
    }

    @Override
    public Button vizualiareButton() {
        return veziUtilizatori;
    }

    public void adaugareOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.adaugareUtilizator();
    }
    public void stergereOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.stergereUtilizator();
    }
    public void actualizareOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.actualizareUtilizator();
    }

    public void inapoiOnAction(ActionEvent event) throws IOException {
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.inapoiOnAction();

    }
    public void vizualizareOnAction(ActionEvent event) throws IOException{
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.vizualizareUtilizatori();
    }


}
