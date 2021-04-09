package View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import presenter.PAbonat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIAbonat  implements IAbonatView, Initializable {

    @FXML
    protected Button dAbonat;
    @FXML
    protected Button veziBiblioteca;
    @FXML
    protected Button veziFiltru;
    @FXML
    protected Button cautaCarte;
    @FXML
    protected Button imprumutareCarte;
    @FXML
    protected Button pAbonat;
    @FXML
    protected Button returnareCarte;
    @FXML
    protected Button pCarte;
    @FXML
    protected Button grafic;
    @FXML
    protected Button pBibliotecar;
    @FXML
    protected TextField titluAles;
    @FXML
    protected Label mAbonat;
    @FXML
    protected ComboBox alegeBiblioteca;
    @FXML
    protected ComboBox alegeFiltru;
    @FXML
    protected ComboBox statistici;

    private String biblioteca;
    private String rolUtilizator;




    @Override
    public String getBibliotecaAleasa() {
        return alegeBiblioteca.getValue().toString();
    }

    @Override
    public String getFiltru() {
       return alegeFiltru.getValue().toString();
    }

    @Override
    public String getTitlu() {
        return titluAles.getText();
    }

    @Override
    public String getStatistici() {
        if (statistici.getSelectionModel().isEmpty() == true)
            return "";
        return statistici.getValue().toString();
    }

    @Override
    public Button getDeautentificare() { return dAbonat; }

    @Override
    public void setBiblioteca(String text) {
        biblioteca = text;
    }

    @Override
    public void setMessage(String text) {
        mAbonat.setText(text);
    }

    @Override
    public String getBiblioteca() {
        return biblioteca;
    }

    @Override
    public String getRolUtilizator() {
        return  rolUtilizator;
    }

    @Override
    public void setRolUtilizator(String text) {
        rolUtilizator = text;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alegeBiblioteca.getItems().removeAll(alegeBiblioteca.getItems());
        alegeBiblioteca.getItems().addAll("Daicoviciu", "Observator", "Mecanica", "Marasti");
        alegeFiltru.getItems().removeAll(alegeFiltru.getItems());
        alegeFiltru.getItems().addAll("domeniu", "autor", "disponibilitate", "titlu");
        statistici.getItems().removeAll(statistici.getItems());
        statistici.getItems().addAll("autor", "domeniu", "editura", "disponibilitate");
    }

    public void veziBibliotecaOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) veziBiblioteca.getScene().getWindow();
        PAbonat abonat = new PAbonat(this);
        abonat.vizualizareCarti();
    }
    public void veziFiltruOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) veziFiltru.getScene().getWindow();
        PAbonat abonat = new PAbonat(this);
        abonat.filtrareCarti();
    }

    public void veziCarteOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) cautaCarte.getScene().getWindow();
        PAbonat abonat = new PAbonat(this);
        abonat.carteCautata();
    }
    public void anulareButtonOnAction(ActionEvent ev) throws IOException {
        Stage stage =(Stage) dAbonat.getScene().getWindow();
        PAbonat abonat = new PAbonat(this);
        abonat.anulareButtonOnAction();
    }

    public void graph(){
        Stage stage =(Stage) grafic.getScene().getWindow();
        PAbonat abonat = new PAbonat(this);
        abonat.graph();
    }

}
