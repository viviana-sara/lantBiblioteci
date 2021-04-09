package View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import presenter.PPersistenaCarte;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUICarte implements IPersistentaCarte,Initializable{
    @FXML
    protected TextField titlu;
    @FXML
    protected TextField autor;
    @FXML
    protected TextField editura;
    @FXML
    protected TextField disponibilitate;
    @FXML
    protected TextField domeniu;
    @FXML
    protected TextField titluNou;
    @FXML
    protected TextField autorNou;
    @FXML
    protected TextField edituraNoua;
    @FXML
    protected TextField disponibilitateNoua;
    @FXML
    protected TextField domeniuNou;
    @FXML
    protected ComboBox bibliotecaNoua;
    @FXML
    protected Label mAdaugare;
    @FXML
    protected Label mActual;
    @FXML
    protected Button stergereCarte;
    @FXML
    protected Button adaugareCarte;
    @FXML
    protected Button actualizareCarte;
    @FXML
    protected Button inapoiCarte;

    protected String biblioteca;
    protected String rolUtilizator;

    public void setB(String text){
        biblioteca= text;
    }
    public void setRolUtilizator(String text){
        rolUtilizator= text;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bibliotecaNoua.getItems().removeAll(bibliotecaNoua.getItems());
        bibliotecaNoua.getItems().addAll("Daicoviciu", "Observator", "Mecanica", "Marasti");

    }

    @Override
    public String getTitlu() {
        return titlu.getText();
    }

    @Override
    public String getEditura() {
        return editura.getText();
    }

    @Override
    public String getDomeniu() {
        return domeniu.getText();
    }

    @Override
    public String getAutor() {
        return autor.getText();
    }

    @Override
    public String getBibliotecaAleasa() {
        if (bibliotecaNoua.getSelectionModel().isEmpty() == true)
            return "";

        return bibliotecaNoua.getValue().toString();
    }

    @Override
    public String getBiblioteca() {
        return biblioteca;
    }

    @Override
    public int getDisponibilitate() {

        if (disponibilitate.getText().equals("") == false)
            return Integer.parseInt(disponibilitate.getText());
        else
            return 0;
    }

    @Override
    public String getTitluNou() {
        return titluNou.getText();
    }

    @Override
    public String getEdituraNoua() {
        return edituraNoua.getText();
    }

    @Override
    public String getDomeniuNou() {
        return domeniuNou.getText();
    }

    @Override
    public String getAutorNou() {
        return autorNou.getText();
    }

    @Override
    public int getDisponibilitateNoua() {
        if (disponibilitateNoua.getText().equals("") == false)
            return Integer.parseInt(disponibilitateNoua.getText());
        else
                return 0;
    }

    @Override
    public void setActualizareMessage(String text) {
        mActual.setText(text);
    }

    @Override
    public void setAdaugareMessagge(String text) {
        mAdaugare.setText(text);
    }

    @Override
    public String getRolUtilizator() {
        return rolUtilizator;
    }

    @Override
    public Button getInapoi() {
        return inapoiCarte;
    }

    public void adaugareOnAction(ActionEvent event) throws IOException{
        PPersistenaCarte persistenta = new PPersistenaCarte(this);
        persistenta.adaugareCarte();
    }
    public void stergereOnAction(ActionEvent event) throws IOException{
        PPersistenaCarte persistenta = new PPersistenaCarte(this);
        persistenta.stergereCarte();
    }
    public void actualizareOnAction(ActionEvent event) throws IOException{
        PPersistenaCarte persistenta = new PPersistenaCarte(this);
        persistenta.actualizareCarte();
    }

    public void inapoiOnAction(ActionEvent event) throws IOException {
        PPersistenaCarte persistenta = new PPersistenaCarte(this);
        persistenta.inapoiOnAction();
    }
}
