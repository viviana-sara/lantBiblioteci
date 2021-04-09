package presenter;

import Model.Carte;
import Model.CartePersistenta;
import View.IAbonatView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import javax.management.openmbean.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PAbonat {
    IAbonatView abonat;
    CartePersistenta persistenta;

    public PAbonat(IAbonatView view ){
        abonat = view;
        persistenta = new CartePersistenta();
    }

    public void vizualizareCarti() {
        String bibliteca = abonat.getBibliotecaAleasa();
        List<Carte> carti = persistenta.cautareCartiBiblioteca(bibliteca);
        System.out.println(carti.size());
        if (carti.size() == 0) {
            abonat.setMessage("Nu exista nicio carte");
        }

        String titlu;
        String autor;
        String editura;
        String domeniu;
        String disponibilitate;

        int i = 0;

        if (carti.size() > 0) {
            String[] columnNames = {"Titlu",
                    "Autor",
                    "Editura",
                    "Domeniu",
                    "Disponibilitate"};
            Object[][] data = new Object[carti.size()][];
            for (Carte carte : carti) {
                titlu = carte.getTitlu();
                autor = carte.getAutor();
                editura = carte.getEditura();
                domeniu = carte.getDomeniu();
                disponibilitate = String.valueOf(carte.getDisponibil());
                data[i++] = new Object[]{titlu, autor, editura, domeniu, disponibilitate};
            }


            JTable table = new JTable(data, columnNames);
            Frame f = new JFrame();
            f.setTitle("Vizualizare Carti ");
            JScrollPane sp = new JScrollPane(table);
            f.add(sp);
            f.setSize(500, 200);
            f.setVisible(true);
        }
    }

    public void filtrareCarti(){
        String filtru = abonat.getFiltru();
        List<Carte> carti = persistenta.filtrareCarti(filtru);
        if (carti.size()== 0 )
            abonat.setMessage("Nu exista nicio carte");

        String titlu;
        String autor;
        String editura;
        String domeniu;
        String biblioteca;
        String disponibilitate;

        if (carti.size()>0) {
            String[] columnNames = {"Titlu",
                    "Autor",
                    "Editura",
                    "Domeniu",
                    "Biblioteca",
                    "Disponibilitate"};
            Object[][] data = new Object[carti.size()][];
            for (int i = 0; i < carti.size(); i++) {
                titlu = carti.get(i).getTitlu();
                autor = carti.get(i).getAutor();
                editura = carti.get(i).getEditura();
                domeniu = carti.get(i).getDomeniu();
                biblioteca = carti.get(i).getBiblioteca();
                disponibilitate = String.valueOf(carti.get(i).getDisponibil());
                data[i] = new Object[]{titlu, autor, editura, domeniu, biblioteca, disponibilitate};
            }
            JTable table = new JTable(data, columnNames);
            Frame f = new JFrame();
            f.setTitle("Filtare Carti ");
            JScrollPane sp = new JScrollPane(table);
            f.add(sp);
            f.setSize(500, 200);
            f.setVisible(true);
        }
    }

    public void carteCautata(){
        String titlu = abonat.getTitlu();
        Carte carte = persistenta.cautareCarte(titlu);
        if (carte.getTitlu().equals("")){
            abonat.setMessage("Cartea nu a fost gasita");
            return ;
        }


        if (carte.getDomeniu().equals("")== false) {
            String m = "Cartea " + carte.getTitlu() + " scrisa de " + carte.getAutor() + ", editura " + carte.getEditura() + ", domeniul " + carte.getDomeniu() + " \neste disponibila in " + carte.getDisponibil() + " exemplare.";
            abonat.setMessage(m);
        }
    }


    public BarChart<String,Number> createGraphicsDomenii(){
        List<Carte> carti = persistenta.citire();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Statistici dupa doemniu");
        xAxis.setLabel("Domeniu");
        yAxis.setLabel("Numar carti");

        List<String> lista = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getDomeniu())) {
                int n = domenii.get(c.getDomeniu());
                n += c.getDisponibil();
                domenii.replace(c.getDomeniu(), n);
            } else {
                domenii.put(c.getDomeniu(), c.getDisponibil());
                lista.add(c.getDomeniu());
            }
        }

        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i< lista.size(); i++){
            series1.getData().add(new XYChart.Data(lista.get(i), domenii.get(lista.get(i))));
        }

        bc.getData().add(series1);
        return bc;
    }

    public PieChart createGraphicsDisp(){
        List<Carte> carti = persistenta.citire();

        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        for (int i = 0; i< carti.size(); i++){
            pieChart.add(new PieChart.Data(carti.get(i).getTitlu(), carti.get(i).getDisponibil()));
        }

        PieChart chart = new PieChart(pieChart);

        return chart;
    }

    public BarChart<String,Number> createGraphicsEditura(){
        List<Carte> carti = persistenta.citire();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Statistici dupa editura");
        xAxis.setLabel("Editura");
        yAxis.setLabel("Numar carti");

        List<String> editura = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getEditura())) {
                int n = domenii.get(c.getEditura());
                n += c.getDisponibil();
                domenii.replace(c.getEditura(), n);
            } else {
                domenii.put(c.getEditura(), c.getDisponibil());
                editura.add(c.getEditura());
            }
        }

         XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i< editura.size(); i++){
            series1.getData().add(new XYChart.Data(editura.get(i), domenii.get(editura.get(i))));
        }
        bc.getData().add(series1);
        return bc;
    }


    public PieChart createGraphicsAutor(){
        List<Carte> carti = persistenta.citire();
        List<String> autor = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getAutor())) {
                int n = domenii.get(c.getAutor());
                n += c.getDisponibil();
                domenii.replace(c.getAutor(), n);
            } else {
                domenii.put(c.getAutor(), c.getDisponibil());
                autor.add(c.getAutor());
            }
        }

        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        for (int i = 0; i< autor.size(); i++){
            pieChart.add(new PieChart.Data(autor.get(i), domenii.get(autor.get(i))));
        }

        PieChart chart = new PieChart(pieChart);

        return chart;
    }

    public void graph(){
        String s = abonat.getStatistici();

        BarChart<String,Number> b;
        PieChart pieChart;
        Stage stage = new Stage();

        if (s.equals("domeniu")){
            b = createGraphicsDomenii();
            Scene scene  = new Scene(b,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("editura")){
            b = createGraphicsEditura();
            Scene scene  = new Scene(b,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("disponibilitate")){
            pieChart = createGraphicsDisp();
            Scene scene  = new Scene(pieChart,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("autor")){
            pieChart = createGraphicsAutor();
            Scene scene  = new Scene(pieChart,500,500);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void anulareButtonOnAction() throws IOException { //inchide aplicatia
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Autentificare.fxml"));
        Parent root =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        javafx.scene.control.Button button= new Button();
        Stage stage2 =(Stage) abonat.getDeautentificare().getScene().getWindow();
        stage2.close();
    }

}
