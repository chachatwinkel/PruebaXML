/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebaxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author samah
 */
public class PruebaXML {

    public static void main(String[] args) {
       
         try {
            //esto para crear XML
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.newDocument();

            Element raiz = doc.createElement("objetivos");
            doc.appendChild(raiz);

            // para leer el fichero
            BufferedReader br = new BufferedReader(
                    new FileReader("estado_sistemas.txt"));

            String linea;
                
               while ((linea = br.readLine()) != null) {

              String[] partes = linea.split(",");

                 if (partes.length == 3) {

                 String objetivo = partes[0].trim();
                 String componente = partes[1].trim();
                 String estado = partes[2].trim();

        if (estado.equalsIgnoreCase("Critico")) {

            Element sistema = doc.createElement("sistema");
            sistema.setAttribute("objetivo", objetivo);

            Element eComp = doc.createElement("componente");
            eComp.appendChild(doc.createTextNode(componente));

            sistema.appendChild(eComp);
            raiz.appendChild(sistema);
        }
    }
}

            // y por ultimo guardamos el xml
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            t.transform(
                    new DOMSource(doc),
                    new StreamResult("objetivos_sabotaje.xml")
            );

            System.out.println("XML creado correctamente.");
            
            

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
        }
    
    }
}
