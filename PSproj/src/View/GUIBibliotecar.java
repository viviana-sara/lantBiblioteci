package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import presenter.PBibliotecar;

import java.io.IOException;

public class GUIBibliotecar extends GUIAbonat implements Initializable {

    protected String bilioteca;
    protected String rolUtilizator;


    public void imprumutareCarteOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.imprumutareCarte();
    }
    public void returnareOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.returnareCarte();
    }

    public void pAbonatOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.pAbonatOnAction();
    }
    public void pCarteOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.pCarteOnAction();
    }

    public void salvareRapoarteOnAction(ActionEvent event){
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.generareCSV();
    }

}
