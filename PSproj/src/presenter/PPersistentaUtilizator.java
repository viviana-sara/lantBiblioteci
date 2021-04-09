package presenter;

import Model.Utilizator;
import Model.UtilizatorPersistenta;
import View.GUIAdministrator;
import View.GUIBibliotecar;
import View.IAbonatView;
import View.IPersistentaUtilizator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PPersistentaUtilizator {
    IPersistentaUtilizator abonat;
    UtilizatorPersistenta pUtilizator;

    public PPersistentaUtilizator(IPersistentaUtilizator view){
        abonat = view;
        pUtilizator = new UtilizatorPersistenta();
    }

    public void adaugareUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();

        if (nume.equals("") || cont.equals("") || pass.equals("") || nume.equals("") || cont.equals("") || pass.equals("") || biblioteca.equals("")){
            abonat.setAdaugare("Adaugati toate detaliile");
            return;
        }

        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== false){
            abonat.setAdaugare("Utilizator deja existent!");
            System.out.println("utilizator inexistent");
            return;
        }
        else{
            boolean ok = pUtilizator.salvareUtilizator(new Utilizator(nume, biblioteca,rol,cont,pass));
            if (ok == true) {
                abonat.setAdaugare(rol + "ul " + nume + " a fost adaugat cu succes!");
                System.out.println("adaugat");
            }
            else{
                abonat.setAdaugare(rol+ "ul "+nume+" nu a putut fi adaugat!");
                System.out.println("neadaugat");
            }

        }
    }

    public void stergereUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();

        if (nume.equals("") || cont.equals("") || pass.equals("") || nume.equals("") || cont.equals("") || pass.equals("") || biblioteca.equals("")){
            abonat.setAdaugare("Adaugati toate detaliile");
            return;
        }

        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== true){
            abonat.setAdaugare(rol+ "ul "+nume+" nu exista!");
            System.out.println("existent");
            return;
        }
        else{
            if (utilizator.getBiblioteca().equals(biblioteca)==false){
                abonat.setAdaugare( "Nu puteti sterge un "+rol+" din alta biblioteca!");
                System.out.println("alta biblioteca");
                return;
            }
            boolean ok = pUtilizator.stergereUtilizator(utilizator);
            if (ok == true) {
                abonat.setAdaugare(rol + "ul " + nume + " a fost sters cu succes!");
                System.out.println("sters");
            }
            else{
                abonat.setAdaugare(rol+ "ul "+nume+" nu a putut fi sters!");
                System.out.println("nesters");
            }

        }
    }

    public void actualizareUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();
        String numeNou = abonat.getNumeNou();
        String contNou = abonat.getContNou();
        String passNou = abonat.getPassNou();
        String bibliotecaActualizare = abonat.getBibliotecaActualizare();

        if (numeNou.equals("") || contNou.equals("") || passNou.equals("") || numeNou.equals("") || contNou.equals("") || passNou.equals("") || bibliotecaActualizare.equals("")){
            abonat.setActualizare("Adaugati toate detaliile");
            return;
        }

        if (nume.equals("") || cont.equals("") || pass.equals("") || nume.equals("") || cont.equals("") || pass.equals("") || biblioteca.equals("")){
            abonat.setAdaugare("Adaugati toate detaliile");
            return;
        }


        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== true){
            abonat.setActualizare(rol+ "ul "+nume+" nu exista!");
            System.out.println("inexistent");
            return;
        }
        else{
            if (utilizator.getBiblioteca().equals(biblioteca)==false){
                abonat.setActualizare( "Nu puteti actualiza un "+rol+" din alta biblioteca!");
                System.out.println("alta biblioteca");
                return;
            }
            Utilizator nou = new Utilizator(numeNou,bibliotecaActualizare,rol,contNou,passNou);
            boolean ok = pUtilizator.actualizareUtilizator(nou,utilizator);
            if (ok == true){
                abonat.setActualizare(rol+ "ul "+nume+" a fost actualizat cu succes!");
                System.out.println("actualizat");
            }
            else{
                abonat.setActualizare(rol+ "ul "+nume+" nu a putut fi actualizat!");
                System.out.println("neactualizat");
            }
        }


    }


    public void vizualizareUtilizatori(){
        String magazin = abonat.getBibloteca();
        List<Utilizator> utilizatori  = pUtilizator.cautareUtilizatoriBiblioteca(magazin);
        System.out.println(utilizatori.size());
        if (utilizatori.size()==0){
            abonat.setAdaugare("Nu exista niciun utilizator in " + magazin);
            return;
        }
        String nume;
        String cont;
        String rol;
        int i = 0;

        if (utilizatori.size()>0) {
            String[] columnNames = {"Nume",
                    "Cont",
                    "rol"};
            Object[][] data = new Object[utilizatori.size()][];
            for (Utilizator utilizator: utilizatori){
                nume = utilizator.getNume();
                cont = utilizator.getCont();
                rol = utilizator.getRol();
                data[i++] = new Object[]{nume, cont, rol};
            }

            JTable table = new JTable(data, columnNames);
            Frame f = new JFrame();
            f.setTitle("Vizualizare Utilizatori ");
            JScrollPane sp = new JScrollPane(table);
            f.add(sp);
            f.setSize(500, 200);
            f.setVisible(true);
        }
    }

    public void inapoiOnAction() throws IOException {
        if (abonat.getRolUtilizator().equals("bibliotecar")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIBibliotecar.fxml"));
            Parent root =(Parent) loader.load();
            GUIBibliotecar bibliotecar = loader.getController();
            bibliotecar.setBiblioteca(abonat.getBibloteca());
            bibliotecar.setRolUtilizator("bibliotecar");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) abonat.inapoiButton().getScene().getWindow();
            stage2.close();
        }
        if (abonat.getRolUtilizator().equals("administrator")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIAdministrator.fxml"));
            Parent root =(Parent) loader.load();
            GUIAdministrator administrator = loader.getController();
            administrator.setBiblioteca(abonat.getBibloteca());
            administrator.setRolUtilizator("administrator");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) abonat.inapoiButton().getScene().getWindow();
            stage2.close();
        }

    }

}
