package Model;

public class Carte {
    private String titlu;
    private String autor;
    private String editura;
    private String domeniu;
    private int disponibil;
    private String biblioteca;

    public Carte(){
        titlu = "";
        autor = "";
        editura = "";
        domeniu = "";
        biblioteca ="";
        disponibil = 0;

    }

    public Carte(String titlu, String autor, String editura, String domeniu , String biblioteca,int  disponibil){
        this.titlu = titlu;
        this.autor= autor;
        this.editura = editura;
        this.disponibil = disponibil;
        this.domeniu= domeniu;
        this.biblioteca = biblioteca;
    }

    public Carte(Carte carte){
        this.titlu = carte.titlu;
        this.autor= carte.autor;
        this.editura = carte.editura;
        this.disponibil =carte. disponibil;
        this.domeniu= carte.domeniu;
        this.biblioteca = carte.biblioteca;
    }

    public String getTitlu() { return titlu; }

    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getAutor() { return autor; }

    public void setAutor(String autor) { this.autor = autor; }

    public String getEditura() { return editura; }

    public void setEditura(String editura) { this.editura = editura; }

    public String getDomeniu() { return domeniu; }

    public void setDomeniu(String domeniu) { this.domeniu = domeniu; }

    public int getDisponibil() { return disponibil; }

    public void setDisponibil(int disponibil) { this.disponibil = disponibil; }

    public String getBiblioteca() { return biblioteca; }

    public void setBiblioteca(String biblioteca) { this.biblioteca = biblioteca; }
}
