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

     public static void Write(List<MessageType> msgsList , File outputFile) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.itico.generatedXmlClasses");
            
            ObjectFactory factory = new ObjectFactory();
            MessagesType fullMsgNode = factory.createMessagesType();
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
             marsh.marshal(msgElement,xmlFileNew);
            //transformToHtml(File xmlFile);

        } catch (JAXBException ex) {
            Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
         } //catch (FileNotFoundException ex) {

    }

//    public static void readXml() {
//        try {
//            JAXBContext context = JAXBContext.newInstance("com.itico.generated");
//            Unmarshaller unmarsh = context.createUnmarshaller();
//            JAXBElement MessageJaxb = (JAXBElement) unmarsh.unmarshal(new File("src/main/java/com/itico/xmlchat/MessageXml.xml"));
//             MessagesType fullMsg = (MessagesType) MessageJaxb.getValue();
//            
//            List<MessageType> chatMessage = fullMsg.getMessage();
//            
//
//            for (MessageType msg : chatMessage) {
//                System.out.println("Message from " + msg.getFrom());
//                System.out.println("Message To " + msg.getTo());
//                System.out.println("Message body " + msg.getBody());
//                System.out.println("Message date " + msg.getDate());
//                System.out.println("Message color " + msg.getColor());
//                System.out.println("Message fontfamily " + msg.getFont().getFontFamily());
//                System.out.println("Message fontsize " + msg.getFont().getFontSize());
//                System.out.println("Message fonttype " + msg.getFont().getFontType());
//                System.out.println("----------------------------");
//
//            }
//            //JAXBElement msgElement = factory.createMessages(fullMsg);
//            //Marshaller marsh = context.createMarshaller();
//            //marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            //marsh.marshal(msgElement, new FileOutputStream("src/main/java/com/itico/xmlchat/output.xml"));
//
//        } catch (JAXBException ex) {
//            Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    
//    private static void transformToHtml(String LocationUrl , String fileName , String xmlFile)
//    {
//        
//        try {
//            DocumentBuilderFactory docBuildfactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docBuildfactory.newDocumentBuilder();
//            Document document = docBuilder.parse(new InputSource(new InputStreamReader(new FileInputStream(LocationUrl+"/"+fileName+".xml"))));
//            TransformerFactory xformer = TransformerFactory.newInstance();
//            
//            Source xslDoc=new StreamSource("src/main/resources/xmlResources/MessageXsltDesign.xsl");
//            //Read From Old File That we created
//            //Source xmlDoc=new StreamSource("src/main/java/com/itico/xmlchat/MessageXml.xml");
//            //Read From New file the app create
//            Source xmlDoc = new StreamSource(xmlFile);
//            String outputFileName = LocationUrl+"/"+fileName +".html";
//            
//            OutputStream htmlFile=new FileOutputStream(outputFileName);
//            Transformer trasform=xformer.newTransformer(xslDoc);
//            trasform.transform(xmlDoc, new StreamResult(htmlFile));
//        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
//            Logger.getLogger(WriteXml.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//    }
//    
//    
//    public static void main(String[] args) {
//        
//        //Just For Test
//        MessageType testMsg = new MessageType();
//        testMsg.setFrom("Ahmed");
//        testMsg.setTo("Mohamed");
//        testMsg.setBody("This is For Just testing The Funtion of the Write Method");
//        testMsg.setDate("11/2/2018");
//        testMsg.setColor("Blue");
//        FontType font =new FontType();
//        font.setFontFamily("Ariel");
//        font.setFontSize("61");
//        font.setFontType("Italic");
//        testMsg.setFont(font);
//        List<MessageType> messageList = new ArrayList<>();
//        messageList.add(testMsg);
//        messageList.add(testMsg);
//        Write(messageList ,"M:\\Gawish", "BassemChatS");
//        //readXml();
//        
//        
//        
//    }

}
