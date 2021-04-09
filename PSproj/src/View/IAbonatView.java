package View;

import javafx.scene.control.Button;

public interface IAbonatView {
    String getBibliotecaAleasa();
    String getFiltru();
    String getTitlu();
    String getStatistici();
    Button getDeautentificare();
    void setBiblioteca(String text);
    void setMessage(String text);
    String getBiblioteca();
    String getRolUtilizator();
    void setRolUtilizator(String text);

}
