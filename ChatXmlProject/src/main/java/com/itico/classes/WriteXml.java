/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itico.classes;

import com.itico.generatedXmlClasses.MessagesType;
import com.itico.generatedXmlClasses.FontType;
import com.itico.generatedXmlClasses.MessageType;
import com.itico.generatedXmlClasses.ObjectFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
public class WriteXml {

     public void Write(List<MessageType> msgsList , File outputFile , String ownerName) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.itico.generatedXmlClasses");
            
            ObjectFactory factory = new ObjectFactory();
            MessagesType fullMsgNode = factory.createMessagesType();
            fullMsgNode.setOwner(ownerName);
           for(MessageType saveMsg: msgsList)
           {
               MessageType newMessage = factory.createMessageType();
               newMessage.setFrom(saveMsg.getFrom());
               newMessage.setTo(saveMsg.getTo());
               newMessage.setBody(saveMsg.getBody());
               newMessage.setDate(saveMsg.getDate());
               newMessage.setColor(saveMsg.getColor());
               newMessage.setFont(saveMsg.getFont());
               fullMsgNode.getMessage().add(newMessage);
           }
            
            JAXBElement msgElement = factory.createMessages(fullMsgNode);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type='text/xsl' href='"+outputFile.getParent()+"/MessageXsltDesign.xsl' ?>");
            marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, outputFile.getParent() + "/MessageSchema.xsd");
            //String xmlFileName = locationUrl + "/" + fileName+ ".xml";
//            marsh.marshal(msgElement, new FileOutputStream(xmlFileName));
            FileOutputStream xmlFileNew  =new FileOutputStream(outputFile);
            //saveFileInternal(getClass().getResource("MessageXsltDesign.xsl").openStream(), outputFile.getParent()+outputFile.getName()+".xsl");
            //saveFileInternal(getClass().getResource("MessageXsltDesign.xsl").openStream(), outputFile.getParent()+outputFile.getName()+".xsl");
            marsh.marshal(msgElement,xmlFileNew);
            //transformToHtml(File xmlFile);

        } catch (JAXBException ex) {
            Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
         } //catch (FileNotFoundException ex) {

    }

     public static void saveFileInternal(InputStream is, String path) 
     {
        Thread threadOne = new Thread( () -> {
        
            FileOutputStream os = null;
            try {
                File newFile = new File(path);
                os = new FileOutputStream(newFile);
                int readByte ; 
                while((readByte=is.read())!= -1){
                    os.write(readByte);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    os.close();
                } catch (IOException ex) {
                  ex.printStackTrace();
                }
            }
        
        });
         
        threadOne.start();
          
      
     }
}
