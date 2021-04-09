package presenter;

import Model.Carte;
import Model.CartePersistenta;
import Model.Utilizator;
import View.GUIAdministrator;
import View.GUIBibliotecar;
import View.IPersistentaCarte;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PPersistenaCarte {
    IPersistentaCarte carte;
    CartePersistenta persistenta;

    public PPersistenaCarte(IPersistentaCarte carte){
        this.carte=carte;
        persistenta = new CartePersistenta();
    }

    private Carte getItem(String text){
        Carte carte1 = new Carte();

        if (text.equals("vechi")){
            if (carte.getTitlu().equals("") || carte.getAutor().equals("") || carte.getEditura().equals("") || carte.getDomeniu().equals("") || carte.getBiblioteca().equals("") || carte.getDisponibilitate() == 0){
                carte.setAdaugareMessagge("Complectati toate detalile!");
                return new Carte();
            }
            else
            return new Carte(carte.getTitlu(),carte.getAutor(),carte.getEditura(),carte.getDomeniu(),carte.getBiblioteca(),carte.getDisponibilitate());
        }
        if (text.equals("nou")){
            if (carte.getTitluNou().equals("") || carte.getAutorNou().equals("") || carte.getEdituraNoua().equals("") || carte.getDomeniuNou().equals("") || carte.getBibliotecaAleasa().equals("") || carte.getDisponibilitateNoua() == 0){
                carte.setActualizareMessage("Complectati toate detalile!");
                return new Carte();
            }
            else
            return new Carte(carte.getTitluNou(),carte.getAutorNou(),carte.getEdituraNoua(),carte.getDomeniuNou(),carte.getBibliotecaAleasa(),carte.getDisponibilitateNoua());
        }
        return carte1;
    }

    public void adaugareCarte(){
        Carte carte1 = getItem("vechi");
        Carte carte2 = persistenta.cautareCarte(carte1.getTitlu());

        if (carte1.getTitlu().equals("") || carte1.getAutor().equals("") || carte1.getEditura().equals("") || carte1.getDomeniu().equals("") || carte1.getBiblioteca().equals("") || carte1.getDisponibil() == 0) {
            return;
        }

        if (carte2.getTitlu().equals("")== false){
            carte.setAdaugareMessagge("Carte deja existenta! Puteti actualiza cartea");
            return;
        }
        else{
            boolean ok = persistenta.salvareCarte(carte1);
            if (ok == true) {
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu()+ " a fost adaugata cu succes!");
               return;
            }
            else{
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu() + "  nu a putut fi adaugata!");
                return;
            }

        }
    }

    public void stergereCarte(){
        Carte carte1 = getItem("vechi");
        Carte carte2 = persistenta.cautareCarte(carte1.getTitlu());
        String biblioteca = carte.getBiblioteca();

        if (carte1.getTitlu().equals("") || carte1.getAutor().equals("") || carte1.getEditura().equals("") || carte1.getDomeniu().equals("") || carte1.getBiblioteca().equals("") || carte1.getDisponibil() == 0) {
            return;
        }

        if (carte2.getTitlu().equals("")== true){
            carte.setAdaugareMessagge("Carte nu exista! Puteti adauga cartea!");
            return;
        }
        else{
            if (biblioteca.equals(carte1.getBiblioteca())==false){
                carte.setAdaugareMessagge("Nu puteti sterge o carte din alta biblioteca!");
                return;
            }
            boolean ok = persistenta.stergereCarte(carte1);
            if (ok == true) {
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu()+ " a fost stearsa cu succes!");
                return;
            }
            else{
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu() + "  nu a putut fi stearsa!");
                return;
            }

        }
    }

    public void actualizareCarte(){
        Carte carteVechi = getItem("vechi");
        Carte carteNou = getItem("nou");
        Carte c = persistenta.cautareCarte(carteVechi.getTitlu());
        String biblioteca = carte.getBiblioteca();

        if (carteVechi.getTitlu().equals("") || carteVechi.getAutor().equals("") || carteVechi.getEditura().equals("") || carteVechi.getDomeniu().equals("") || carteVechi.getBiblioteca().equals("") || carteVechi.getDisponibil() == 0) {
            return;
        }
        if (carteVechi.getTitlu().equals("") || carteVechi.getAutor().equals("") || carteVechi.getEditura().equals("") || carteVechi.getDomeniu().equals("") || carteVechi.getBiblioteca().equals("") || carteVechi.getDisponibil() == 0) {
            return;
        }

        if (c.getTitlu().equals("")== true){
            carte.setActualizareMessage("Cartea nu exista!");
            return;
        }
        else{
            if (carteVechi.getBiblioteca().equals(biblioteca)==false){
                carte.setActualizareMessage("Nu puteti actualiza o carte din alta biblioteca!");
                return;
            }
            boolean ok = persistenta.actualizareCarte(carteVechi,carteNou);
            if (ok == true){
                carte.setActualizareMessage("Actualizarea s-a realizat cu succes!");
                return;            }
            else{
                carte.setActualizareMessage("Actualizarea nu s-a putut finisa!");
            }
        }


    }

    public void inapoiOnAction() throws IOException {
        if (carte.getRolUtilizator().equals("bibliotecar")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIBibliotecar.fxml"));
            Parent root =(Parent) loader.load();
            GUIBibliotecar bibliotecar = loader.getController();
            bibliotecar.setBiblioteca(carte.getBiblioteca());
            bibliotecar.setRolUtilizator("bibliotecar");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) carte.getInapoi().getScene().getWindow();
            stage2.close();
        }
        if (carte.getRolUtilizator().equals("administrator")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIAdministrator.fxml"));
            Parent root =(Parent) loader.load();
            GUIAdministrator administrator = loader.getController();
            administrator.setBiblioteca(carte.getBiblioteca());
            administrator.setRolUtilizator("administrator");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) carte.getInapoi().getScene().getWindow();
            stage2.close();
        }
    }
}
