package org.damato;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class crearXml {
    public static void main(String[] args) {
        Producto p1 = new Producto("Ordenador", 1245);
        Producto p2 = new Producto("Grafica", 1245);
        Producto p3 = new Producto("DDDR", 124);
        Producto p4 = new Producto("Ventiladores", 145);
        Producto p5 = new Producto("Teclado", 45);
        Producto p6 = new Producto("Raton", 45);

        List<Producto> productos = new ArrayList<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);

        File xmlFile = new File("./src/main/resources/productos.xml");

        //generadorUno(productos, xmlFile);

        leerXML(xmlFile);


    }

    private static void leerXML(File xmlFile) {
        try {

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

            //nomalizar xml
            document.getDocumentElement().normalize();

            //obtener lista de nodos
            NodeList productos = document.getElementsByTagName("producto");

            for (int i = 0; i < productos.getLength(); i++) {
                Node producto = productos.item(i);
                if (producto.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) producto;


                    //obtener y mostrar nombre
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Precio :" + elemento.getElementsByTagName("precio").item(0).getTextContent());


                }
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }


    private static void generadorUno(List<Producto> productos, File xmlFile) {
        try {
            GeneradorXml generador = new GeneradorXml();

            generador.generarXML(productos, xmlFile);

            System.out.println("Archivo XML generado correctamente.");
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
