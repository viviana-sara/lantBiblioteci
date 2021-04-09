package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class UtilizatorPersistenta {
    private String numeFisier = "utilizator";
    private String filePath = "/Users/mesaros.viviana/Desktop/PS/PSproj/src/Model/file.xml";

    public List<Utilizator> citire(){
        List<Utilizator> utilizatori = new ArrayList<>();
        String nume;
        String rol;
        String cont;
        String parola;
        String biblioteca;


        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    nume = eElement.getElementsByTagName("nume").item(0).getTextContent();
                    rol = eElement.getElementsByTagName("rol").item(0).getTextContent();
                    cont = eElement.getElementsByTagName("cont").item(0).getTextContent();
                    parola = eElement.getElementsByTagName("parola").item(0).getTextContent();
                    biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();

                    utilizatori.add(new Utilizator(nume, biblioteca, rol, cont, parola));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return utilizatori;
    }

    public boolean salvareUtilizator(Utilizator utilizator){
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            //doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            int nr = nodeList.getLength();

            Element newElem = doc.createElement("utilizator");
            Element nume = doc.createElement("nume");
            Element nrLegitimatie = doc.createElement("biblioteca");
            Element rol = doc.createElement("rol");
            Element cont = doc.createElement("cont");
            Element parola = doc.createElement("parola");
            nume.appendChild(doc.createTextNode(utilizator.getNume()));
            nrLegitimatie.appendChild(doc.createTextNode(String.valueOf(utilizator.getBiblioteca())));
            rol.appendChild(doc.createTextNode(utilizator.getRol()));
            cont.appendChild(doc.createTextNode(utilizator.getCont()));
            parola.appendChild(doc.createTextNode(utilizator.getParola()));
            newElem.appendChild(nume);
            newElem.appendChild(nrLegitimatie);
            newElem.appendChild(rol);
            newElem.appendChild(cont);
            newElem.appendChild(parola);
            root.appendChild(newElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            if (nr+1 == doc.getElementsByTagName("utilizator").getLength()) {
                return true;

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return false;
    }

    public boolean actualizareUtilizator(Utilizator nou, Utilizator vechi){
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
           // doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String cont = eElement.getElementsByTagName("cont").item(0).getTextContent();

                    if (cont.equals(vechi.getCont())){
                        eElement.getElementsByTagName("nume").item(0).setTextContent(nou.getNume());
                        eElement.getElementsByTagName("biblioteca").item(0).setTextContent(String.valueOf(nou.getBiblioteca()));
                        eElement.getElementsByTagName("rol").item(0).setTextContent(nou.getRol());
                        eElement.getElementsByTagName("parola").item(0).setTextContent(nou.getParola());
                        eElement.getElementsByTagName("cont").item(0).setTextContent(nou.getCont());
                        break;
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String cont = eElement.getElementsByTagName("cont").item(0).getTextContent();
                    String nume = eElement.getElementsByTagName("nume").item(0).getTextContent();
                    String rol = eElement.getElementsByTagName("rol").item(0).getTextContent();
                    String parola = eElement.getElementsByTagName("parola").item(0).getTextContent();
                    String biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();

                    if (cont.equals(nou.getCont()) && nume.equals(nou.getNume()) && rol.equals(nou.getRol()) && parola.equals(nou.getParola())&& biblioteca.equals(nou.getBiblioteca()));
                        return true;

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Utilizator cautareUtilizator(String cont, String parola){
        Utilizator found = new Utilizator();
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            System.out.println(nodeList.getLength());

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("cont").item(0).getTextContent().equals(cont) && eElement.getElementsByTagName("parola").item(0).getTextContent().equals(parola)){

                       found.setCont(eElement.getElementsByTagName("cont").item(0).getTextContent());
                       found.setParola(eElement.getElementsByTagName("parola").item(0).getTextContent());
                       found.setRol(eElement.getElementsByTagName("rol").item(0).getTextContent());
                       found.setBiblioteca(eElement.getElementsByTagName("biblioteca").item(0).getTextContent());
                       found.setNume(eElement.getElementsByTagName("nume").item(0).getTextContent());
                       return found;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return found;
    }


    public List<Utilizator> filtrareUtilizatori(String rolUtilizator){
        List<Utilizator> utilizatori = new ArrayList<>();
        String nume;
        String rol;
        String cont;
        String parola;
        String biblioteca;


        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("rol").item(0).getTextContent().equals(rolUtilizator)){
                        nume = eElement.getElementsByTagName("nume").item(0).getTextContent();
                        rol = eElement.getElementsByTagName("rol").item(0).getTextContent();
                        cont = eElement.getElementsByTagName("cont").item(0).getTextContent();
                        parola = eElement.getElementsByTagName("parola").item(0).getTextContent();
                        biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();

                        utilizatori.add(new Utilizator(nume, biblioteca, rol, cont, parola));
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return utilizatori;
    }

    public boolean stergereUtilizator(Utilizator utilizator){
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            int nr = nodeList.getLength();

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("cont").item(0).getTextContent().equals(utilizator.getCont()) && eElement.getElementsByTagName("parola").item(0).getTextContent().equals(utilizator.getParola()) && eElement.getElementsByTagName("biblioteca").item(0).getTextContent().equals(utilizator.getBiblioteca())){
                       node.getParentNode().removeChild(node);
                       break;
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            nodeList = doc.getElementsByTagName(numeFisier);
            if (nr - 1 == nodeList.getLength())
                return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return false;
    }


    public List<Utilizator> cautareUtilizatoriBiblioteca(String biblioteca){
        List<Utilizator> utilizatori = new ArrayList<>();
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("biblioteca").item(0).getTextContent().equals(biblioteca)){
                        Utilizator found = new Utilizator();
                        found.setNume(eElement.getElementsByTagName("nume").item(0).getTextContent());
                        found.setBiblioteca(eElement.getElementsByTagName("biblioteca").item(0).getTextContent());
                        found.setCont(eElement.getElementsByTagName("cont").item(0).getTextContent());
                        found.setParola(eElement.getElementsByTagName("parola").item(0).getTextContent());
                        found.setRol(eElement.getElementsByTagName("rol").item(0).getTextContent());
                        utilizatori.add(found);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return utilizatori;
    }
}
