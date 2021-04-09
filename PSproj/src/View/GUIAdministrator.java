package View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presenter.PAdministrator;

import java.io.IOException;

public class GUIAdministrator extends GUIBibliotecar implements Initializable {

    public void pBibliotecarOnAction(javafx.event.ActionEvent event) throws IOException {
        PAdministrator administrator  = new PAdministrator(this);
        administrator.pBibliotecarOnAction();
    }

}
