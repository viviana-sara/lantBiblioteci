package Model;

import java.util.ArrayList;
import java.util.List;

public class Persoane {
    private List<Persoana> listaPersoane;

    public Persoane(){
        listaPersoane = new ArrayList<>();
    }

    public Persoane(Persoane lista){
        listaPersoane = (List<Persoana>) lista;
    }

    public Persoane(List<Persoana> lista){
        listaPersoane = lista;
    }

    public List<Persoana> getListaPersoane() { return listaPersoane; }

    public void setListaPersoane(List<Persoana> listaPersoane) { this.listaPersoane = listaPersoane; }
}
