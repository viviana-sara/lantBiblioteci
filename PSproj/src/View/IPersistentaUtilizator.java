package View;

import javafx.scene.control.Button;

public interface IPersistentaUtilizator {
    String getNume();
    String getCont();
    String getPass();
    String getNumeNou();
    String getContNou();
    String getPassNou();
    void setActualizare(String text);
    void setAdaugare(String text);
    String getBibloteca();
    String getRolAdauga();
    String getBibliotecaActualizare();
    String getRolUtilizator();
    Button inapoiButton();
    Button vizualiareButton();

}
