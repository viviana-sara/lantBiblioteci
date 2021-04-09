package View;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import presenter.PAutentificare;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Autentificare implements  Initializable, IAutentificareView {

        @FXML
        private Button autentificare;
        @FXML
        private TextField cont;
        @FXML
        private PasswordField pass;
        @FXML
        private Label mAutentificare;
        @FXML
        private ComboBox selRol;

        private String rolUtilizator;


        public void autentificareOnAction(ActionEvent event) throws IOException {
                PAutentificare p = new PAutentificare(this);
                p.autentificare();
        }


        @Override
        public String getRol() { return selRol.getValue().toString(); }

        @Override
        public void setRol(String rol) {
                rolUtilizator = rol;
        }

        @Override
        public String getCont() {
                return cont.getText();
        }

        @Override
        public String getPass() {
                return pass.getText();
        }

        @Override
        public void setMessage(String text) {
                mAutentificare.setText(text);
        }


        @Override
        public Button getAutentificare() {
                return autentificare;
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                selRol.getItems().removeAll(selRol.getItems());
                selRol.getItems().addAll("administrator", "bibliotecar", "abonat");
                selRol.getSelectionModel().select("abonat");
        }


}
