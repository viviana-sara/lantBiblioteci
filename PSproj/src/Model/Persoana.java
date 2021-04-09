package Model;

public class Persoana {
    protected String nume;
    protected String biblioteca;


    public Persoana(){
        nume = "";
        biblioteca= "";
    }

    public Persoana(Persoana persoana){
        nume = persoana.nume;
        biblioteca = persoana.biblioteca;
    }

    public Persoana(String nume, String biblioteca){
        this.nume= nume;
        this.biblioteca=biblioteca;
    }


    public String getNume() {return nume; }

    public void setNume(String nume) { this.nume = nume; }

    public String getBiblioteca() { return biblioteca; }

    public void setBiblioteca(String biblioteca) { this.biblioteca = biblioteca; }
}
