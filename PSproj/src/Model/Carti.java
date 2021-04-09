package Model;

import java.util.ArrayList;
import java.util.List;

public class Carti {
    private List<Carte> listaCarti;

    public Carti(){
        listaCarti = new ArrayList<>();
    }

    public Carti(Carti carti){
        listaCarti = (List<Carte>) carti;
    }

    public Carti(List<Carte> listaCarti){
        this.listaCarti = listaCarti;
    }

    public List<Carte> getListaCarti() { return listaCarti; }

    public void setListaCarti(List<Carte> listaCarti) { this.listaCarti = listaCarti; }
}
