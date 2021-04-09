package presenter;

import Model.Utilizator;
import Model.UtilizatorPersistenta;
import View.GUIAbonat;
import View.GUIAdministrator;
import View.GUIBibliotecar;
import View.IAutentificareView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PAutentificare {
    private IAutentificareView login;
    private UtilizatorPersistenta persistenta;

    public PAutentificare(IAutentificareView view){
        this.login = view;
        persistenta = new UtilizatorPersistenta();
    }

    public void autentificare() throws IOException {
        int next = 0;
        String cont = login.getCont();
        String pass = login.getPass();
        String rol = login.getRol();

        Utilizator utilizator = persistenta.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")) {
            login.setMessage("Utilizator inexistent");
            return;
        }
        if (utilizator.getRol().equals(rol)== false){
            System.out.println("Roluri: " + utilizator.getRol() + "      " + rol);
            login.setMessage("Utilizatorul nu are acelasi rol cu cel selectat");
            return;
        }
        else{
            login.setMessage("autentificare reusita");
            next = 1;
            login.setRol(rol);
        }

            if (next == 1){
                if (rol.equals("administrator")){
                    FXMLLoader loader = new FXMLLoader(GUIAdministrator.class.getResource("GUIAdministrator.fxml"));
                    Parent root =(Parent) loader.load();
                    GUIAdministrator administrator = loader.getController();
                    administrator.setBiblioteca(getB());
                    administrator.setRolUtilizator("administrator");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage stage2 =(Stage) login.getAutentificare().getScene().getWindow();
                    stage2.close();
                }
                if (rol.equals("abonat")){
                    FXMLLoader loader = new FXMLLoader(GUIAbonat.class.getResource("GUIAbonat.fxml"));
                    Parent root =(Parent) loader.load();
                    GUIAbonat abonat = loader.getController();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage stage2 =(Stage) login.getAutentificare().getScene().getWindow();
                    stage2.close();
                }
                if (rol.equals("bibliotecar")){
                    FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIBibliotecar.fxml"));
                    Parent root =(Parent) loader.load();
                    GUIBibliotecar bibliotecar = loader.getController();
                    bibliotecar.setBiblioteca(getB());
                    bibliotecar.setRolUtilizator("bibliotecar");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage stage2 =(Stage) login.getAutentificare().getScene().getWindow();
                    stage2.close();
                }
            }
    }

    public String getB(){
        String cont = login.getCont();
        String pass = login.getPass();

        Utilizator utilizator = persistenta.cautareUtilizator(cont,pass);
        return utilizator.getBiblioteca();

    }
}
