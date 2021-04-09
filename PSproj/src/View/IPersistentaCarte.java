package View;

import javafx.scene.control.Button;

public interface IPersistentaCarte {
    String getTitlu();
    String getEditura();
    String getDomeniu();
    String getAutor();
    String getBibliotecaAleasa();
    String getBiblioteca();
    int getDisponibilitate();
    String getTitluNou();
    String getEdituraNoua();
    String getDomeniuNou();
    String getAutorNou();
    int getDisponibilitateNoua();;
    void setActualizareMessage(String text);
    void setAdaugareMessagge(String text);
    String getRolUtilizator();
    Button getInapoi();

}
