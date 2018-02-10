/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itico.classes;



import com.sun.xml.internal.org.jvnet.staxex.Base64EncoderStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author bassem
 */
public class TransformXml {
    
     public static void main(String[] args) {
         try {
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             DocumentBuilder builder = factory.newDocumentBuilder();
             Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream("src/main/java/com/itico/xmlchat/MessageXml.xml"))));
             TransformerFactory xformer = TransformerFactory.newInstance();

            Source xslDoc=new StreamSource("src/main/java/com/itico/xmlchat/MessageXsltDesign.xsl");
            //Read From Old File That we created
            //Source xmlDoc=new StreamSource("src/main/java/com/itico/xmlchat/MessageXml.xml");
            //Read From New file the app create
            Source xmlDoc=new StreamSource("src/main/java/com/itico/xmlchat/output.xml");
            String outputFileName="chat.html";

             OutputStream htmlFile=new FileOutputStream(outputFileName);
             Transformer trasform=xformer.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
         } catch (ParserConfigurationException | FileNotFoundException ex) {
             Logger.getLogger(TransformXml.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SAXException ex) {
             Logger.getLogger(TransformXml.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(TransformXml.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerConfigurationException ex) {
             Logger.getLogger(TransformXml.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerException ex) {
             Logger.getLogger(TransformXml.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
