package org.damato;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class GeneradorXml {
    private Document documento;

    public GeneradorXml() throws ParserConfigurationException {
        //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DocumentBuilder builder=factory.newDocumentBuilder();
        //this.documento=builder.newDocument();
        this.documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

    }

    public void generarXML(List<Producto> productos, File xmlFile) throws TransformerException {
        Element raiz = documento.createElement("productos");
        documento.appendChild(raiz);

        for (Producto actual : productos) {
            Element nodoProducto = documento.createElement("producto");
            raiz.appendChild(nodoProducto);

            Element nodoNombre = documento.createElement("nombre");
            nodoProducto.appendChild(nodoNombre);
            Text textNombre = documento.createTextNode(actual.getNombre());
            nodoNombre.appendChild(textNombre);

            Element nodoPrecio = documento.createElement("precio");
            nodoProducto.appendChild(nodoPrecio);
            Text textoPrecio = documento.createTextNode(String.valueOf(actual.getPrecio()));
            nodoPrecio.appendChild(textoPrecio);

        }


        // Escribir el documento XML a un archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(xmlFile);

        transformer.transform(source, result);

       // TransformerFactory.newInstance().newTransformer().transform(new DOMSource(documento),new StreamResult(xmlFile));


    }
}
