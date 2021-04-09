package Model;

public class Utilizator extends Persoana{
    private String rol;
    private String cont;
    private String parola;



    public Utilizator(){
        super();
        rol = "";
        cont = "";
        parola = "";
    }

    public Utilizator(String nume, String biblioteca,String rol, String cont, String parola){
        super(nume,biblioteca);
        this.rol=rol;
        this.parola = parola;
        this.cont = cont;
    }

    public Utilizator(Utilizator utilizator){
        this.rol = utilizator.rol;
        this.parola = utilizator.parola;
        this.cont = utilizator.cont;
    }



    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    public String getCont() { return cont; }

    public void setCont(String cont) { this.cont = cont; }

    public String getParola() { return parola; }

    public void setParola(String parola) { this.parola = parola; }
}
