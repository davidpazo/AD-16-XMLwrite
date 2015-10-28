package XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author oracle
 */
public class XMLwrite {

    String ruta = "/home/oracle/Desktop/Pruebas/Products.txt";
    ArrayList<Products> lis = new ArrayList();
    String[] cod = {"p1", "p2", "p3"};
    String[] desc = {"parafusos", "cravos", "tachas"};
    int[] price = {3, 4, 5};

    public static void main(String[] args) {
        XMLwrite xr = new XMLwrite();
        xr.escribir();
        xr.leer();
        xr.EscribirXML();
        xr.LeerXML();
    }
    public void escribir() {
        try {
            FileOutputStream fos = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < cod.length; i++) {
                Products obj = new Products();
                obj.setCodigo(cod[i]);
                obj.setDescripcion(desc[i]);
                obj.setPrezo(price[i]);
                oos.writeObject(obj);
            }
            Products obj1 = null;
            oos.writeObject(obj1);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error encontrando el archivo");
        } catch (IOException ex) {
            System.out.println("Error de I/O");
        }
    }
    public void leer() {
        try {
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Products obj;
            while ((obj = (Products) ois.readObject()) != null) {
                lis.add(obj);
            }
            for (Products product : lis) {
                System.out.println(product.getCodigo() + " " + product.getDescripcion() + " " + product.getPrezo());
            }
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error encontrando el archivo");
        } catch (IOException ex) {
            System.out.println("Error de I/O");
        } catch (ClassNotFoundException ex) {
            System.out.println("No encuentra la clase... Penoso");
        }
    }
    public void EscribirXML() {
        try {

            XMLStreamWriter xmls = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new FileOutputStream("/home/oracle/Desktop/Pruebas/Products.xml"));
            xmls.writeStartDocument("1.0");
            xmls.writeStartElement("autores");
            xmls.writeStartElement("autor");
            for (Products i : lis) {
                xmls.writeStartElement("producto");
                xmls.writeStartElement("codigo");
                xmls.writeCharacters(i.getCodigo());
                xmls.writeEndElement();
                xmls.writeStartElement("Descripcion");
                xmls.writeCharacters(i.getDescripcion());
                xmls.writeEndElement();
                xmls.writeStartElement("Precio");
                xmls.writeCharacters(Integer.toString(i.getPrezo()));
                xmls.writeEndElement();
                xmls.writeEndElement();
            }
            xmls.writeEndDocument();
            xmls.flush();
            xmls.close();
        } catch (XMLStreamException | FileNotFoundException ex) {
            System.out.println("Excepcion de Streaming,\nFichero no encontrado");
        }
    }
    public void LeerXML() {
        try {
            XMLEventReader xml = XMLInputFactory.newInstance().createXMLEventReader("/home/oracle/Desktop/Pruebas/Products.xml"
                    ,new FileInputStream("/home/oracle/Desktop/Pruebas/Products.xml"));
            while (xml.hasNext()) {
                System.out.println(xml.nextEvent().toString());
            }
            xml.close();

        } catch (XMLStreamException | FileNotFoundException ex) {
            System.out.println("Excepcion de Streaming, \nFichero no encontrado");
        }
    }
}