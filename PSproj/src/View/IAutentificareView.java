package View;

import javafx.scene.control.Button;

public interface IAutentificareView {
    String getRol();
    void setRol(String rol);
    String getCont();
    String getPass();
    void setMessage(String text);
    Button getAutentificare();
}
