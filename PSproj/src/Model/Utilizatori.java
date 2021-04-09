package Model;

import java.util.ArrayList;
import java.util.List;

public class Utilizatori {
    private List<Utilizator> listaUtilizatori;

    public Utilizatori(){
        listaUtilizatori = new ArrayList<>();
    }

    public  Utilizatori(Utilizatori utilizatori){ this.listaUtilizatori = (List<Utilizator>) utilizatori; }

    public Utilizatori(List<Utilizator> listaUtilizatori){ this.listaUtilizatori = listaUtilizatori; }

    public List<Utilizator> getListaUtilizatori() { return listaUtilizatori; }

    public void setListaUtilizatori(List<Utilizator> listaUtilizatori) { this.listaUtilizatori = listaUtilizatori; }

    public Utilizator cautareNume(String nume){
        Utilizator utilizator = new Utilizator();

        for (Utilizator ut: listaUtilizatori
             ) {
            if (ut.getNume().equals(nume)){
                utilizator = ut;
                break;
            }
        }
        return utilizator;
    }
}
