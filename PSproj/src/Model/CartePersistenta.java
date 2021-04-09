package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartePersistenta {
    private String numeFisier = "carte";
    private String filePath = "/Users/mesaros.viviana/Desktop/PS/PSproj/src/Model/file.xml";

    public List<Carte> citire(){
            List<Carte> carti = new ArrayList<>();
            String autor;
            String editura;
            String titlu;
            String domeniu;
            String biblioteca;
            int disponibilitate;


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

                        titlu = eElement.getElementsByTagName("titlu").item(0).getTextContent();
                        editura = eElement.getElementsByTagName("editura").item(0).getTextContent();
                        autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
                        domeniu = eElement.getElementsByTagName("domeniu").item(0).getTextContent();
                        biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();
                        disponibilitate = Integer.parseInt(eElement.getElementsByTagName("disponibilitate").item(0).getTextContent());

                        carti.add(new Carte(titlu,autor,editura,domeniu,biblioteca,disponibilitate));
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return carti;

    }



    public List<Carte> filtrareCarti(String filtru){
        List<Carte> lista = new ArrayList<>();
        Comparator<Carte> compareByAuthor = (Carte c1, Carte c2) ->
                c1.getAutor().compareTo( c2.getAutor() );
        Comparator<Carte> compareByEditura = (Carte c1, Carte c2) ->
                c1.getEditura().compareTo( c2.getEditura() );
        Comparator<Carte> compareByDomeniu = (Carte c1, Carte c2) ->
                c1.getDomeniu().compareTo( c2.getDomeniu() );
        Comparator<Carte> compareByDisponibilitate = (Carte c1, Carte c2) ->
                String.valueOf(c1.getDisponibil()).compareTo( String.valueOf(c2.getDisponibil() ));

        lista = citire();
        if (lista.size() > 1){
            if (filtru.equals(new String("autor")))
                Collections.sort(lista,compareByAuthor);
            if (filtru.equals(new String("editura")))
                Collections.sort(lista,compareByEditura);
            if (filtru.equals(new String("domeniu")))
                Collections.sort(lista,compareByDomeniu);
            if (filtru.equals(new String("disponibilitate")))
                Collections.sort(lista,compareByDisponibilitate);
        }
        return  lista;

    }


    public boolean salvareCarte(Carte carte){
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

            Carte carte1 = cautareCarte(carte.getTitlu());

            if (carte1.getAutor().equals(carte.getAutor())){
                int dis = carte.getDisponibil() + 1;
                carte1.setDisponibil(dis);
                actualizareCarte(carte, carte1);
                return true;
            }
            Element newElem = doc.createElement("carte");
            Element titlu = doc.createElement("titlu");
            Element autor = doc.createElement("autor");
            Element editura = doc.createElement("editura");
            Element domeniu = doc.createElement("domeniu");
            Element biblioteca = doc.createElement("biblioteca");
            Element disponibilitate = doc.createElement("disponibilitate");
            titlu.appendChild(doc.createTextNode(carte.getTitlu()));
            disponibilitate.appendChild(doc.createTextNode(String.valueOf(carte.getDisponibil())));
            autor.appendChild(doc.createTextNode(carte.getAutor()));
            domeniu.appendChild(doc.createTextNode(carte.getDomeniu()));
            editura.appendChild(doc.createTextNode(carte.getEditura()));
            biblioteca.appendChild(doc.createTextNode(carte.getBiblioteca()));
            newElem.appendChild(titlu);
            newElem.appendChild(autor);
            newElem.appendChild(editura);
            newElem.appendChild(domeniu);
            newElem.appendChild(disponibilitate);
            newElem.appendChild(biblioteca);
            root.appendChild(newElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            if (nr+1 == doc.getElementsByTagName("carte").getLength()) {
                return true;

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return false;
    }


    public boolean actualizareCarte(Carte vechi, Carte nou){
        try {

            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            NodeList nodeList = doc.getElementsByTagName(numeFisier);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String titlu = eElement.getElementsByTagName("titlu").item(0).getTextContent();
                    String biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();

                    if (titlu.equals(vechi.getTitlu()) && biblioteca.equals(vechi.getBiblioteca())) {
                        System.out.println("intra la actualizare  si nou = " + nou.getDisponibil());
                        eElement.getElementsByTagName("autor").item(0).setTextContent(nou.getAutor());
                        eElement.getElementsByTagName("disponibilitate").item(0).setTextContent(String.valueOf(nou.getDisponibil()));
                        eElement.getElementsByTagName("editura").item(0).setTextContent(nou.getEditura());
                        eElement.getElementsByTagName("titlu").item(0).setTextContent(nou.getTitlu());
                        eElement.getElementsByTagName("domeniu").item(0).setTextContent(nou.getDomeniu());
                        eElement.getElementsByTagName("biblioteca").item(0).setTextContent(nou.getBiblioteca());
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
                    String titlu = eElement.getElementsByTagName("titlu").item(0).getTextContent();
                   // System.out.println("titlu "+ titlu + "    asteptat " + nou.getTitlu()) ;
                    String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
                   // System.out.println("autor "+ autor + "    asteptat " + nou.getAutor());
                    String editura = eElement.getElementsByTagName("editura").item(0).getTextContent();
                    //System.out.println("editura "+ editura+ "    asteptat " + nou.getEditura());
                    String domeniu = eElement.getElementsByTagName("domeniu").item(0).getTextContent();
                    //System.out.println("domeniu "+ domeniu+ "    asteptat " + nou.getDomeniu());
                    String disponibilitate = eElement.getElementsByTagName("disponibilitate").item(0).getTextContent();
                    //System.out.println("disp "+ disponibilitate+ "    asteptat " + nou.getDisponibil());
                    String biblioteca = eElement.getElementsByTagName("biblioteca").item(0).getTextContent();
                    //System.out.println("biblioteca "+ biblioteca+ "    asteptat " + nou.getBiblioteca()+ "\n\n");

                    if (titlu.equals(nou.getTitlu()) && autor.equals(nou.getAutor()) && domeniu.equals(nou.getDomeniu()) && editura.equals(nou.getEditura()) && Integer.parseInt(disponibilitate) == nou.getDisponibil() && biblioteca.equals(nou.getBiblioteca())) {
                       // System.out.println("disponibilitate actualizare "+disponibilitate);
                        return true;

                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return false;
    }

    public boolean stergereCarte(Carte carte){
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

                    if (eElement.getElementsByTagName("titlu").item(0).getTextContent().equals(carte.getTitlu()) && eElement.getElementsByTagName("biblioteca").item(0).getTextContent().equals(carte.getBiblioteca())){
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


    public Carte cautareCarte(String titlu){
        Carte found = new Carte();
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

                    if (eElement.getElementsByTagName("titlu").item(0).getTextContent().equals(titlu) ){
                        found.setAutor(eElement.getElementsByTagName("autor").item(0).getTextContent());
                        found.setDomeniu(eElement.getElementsByTagName("domeniu").item(0).getTextContent());
                        found.setEditura(eElement.getElementsByTagName("editura").item(0).getTextContent());
                        found.setDisponibil(Integer.parseInt(eElement.getElementsByTagName("disponibilitate").item(0).getTextContent()));
                        found.setTitlu(eElement.getElementsByTagName("titlu").item(0).getTextContent());
                        found.setBiblioteca(eElement.getElementsByTagName("biblioteca").item(0).getTextContent());
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


    public List<Carte> cautareCartiBiblioteca(String biblioteca){
        List<Carte> carti = new ArrayList<>();
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

                    if (eElement.getElementsByTagName("biblioteca").item(0).getTextContent().equals(biblioteca) ){
                        Carte found = new Carte();
                        found.setAutor(eElement.getElementsByTagName("autor").item(0).getTextContent());
                        found.setDomeniu(eElement.getElementsByTagName("domeniu").item(0).getTextContent());
                        found.setEditura(eElement.getElementsByTagName("editura").item(0).getTextContent());
                        found.setDisponibil(Integer.parseInt(eElement.getElementsByTagName("disponibilitate").item(0).getTextContent()));
                        found.setTitlu(eElement.getElementsByTagName("titlu").item(0).getTextContent());
                        found.setBiblioteca(eElement.getElementsByTagName("biblioteca").item(0).getTextContent());
                        carti.add(found);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return carti;
    }

}
