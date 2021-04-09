package presenter;

import Model.CartePersistenta;
import Model.UtilizatorPersistenta;
import View.GUIPersistentaUtilizator;
import View.IAbonatView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PAdministrator {
    IAbonatView abonat;

    public PAdministrator(IAbonatView view){
        abonat = view;
    }
    public void pBibliotecarOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(GUIPersistentaUtilizator.class.getResource("PersistentaAbonat.fxml"));
        Parent root =(Parent) loader.load();
        GUIPersistentaUtilizator persistenta = loader.getController();
        persistenta.setB(abonat.getBiblioteca());
        persistenta.setUtilizator(abonat.getBiblioteca());
        persistenta.setR("bibliotecar");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 =(Stage) abonat.getDeautentificare().getScene().getWindow();
        stage2.close();
    }
}
