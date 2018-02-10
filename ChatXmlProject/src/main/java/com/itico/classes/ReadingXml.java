/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itico.classes;

import com.itico.generated.*;
import com.itico.generated.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author bassem
 */
public class ReadingXml {

    public static void Write(MessageType saveMsg) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.itico.generated");
            
            ObjectFactory factory = new ObjectFactory();
            MessagesType fullMsg = factory.createMessagesType();
           
            MessageType newMessage = factory.createMessageType();
            newMessage.setFrom(saveMsg.getFrom());
            newMessage.setTo(saveMsg.getTo());
            newMessage.setBody(saveMsg.getBody());
            newMessage.setDate(saveMsg.getDate());
            newMessage.setColor(saveMsg.getColor());
            newMessage.setFont(saveMsg.getFont());
            fullMsg.getMessage().add(newMessage);
            JAXBElement msgElement = factory.createMessages(fullMsg);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(msgElement, new FileOutputStream("src/main/java/com/itico/xmlchat/output.xml"));

        } catch (JAXBException ex) {
            Logger.getLogger(ReadingXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadingXml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void readXml() {
        try {
            JAXBContext context = JAXBContext.newInstance("com.itico.generated");
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement MessageJaxb = (JAXBElement) unmarsh.unmarshal(new File("src/main/java/com/itico/xmlchat/MessageXml.xml"));
             MessagesType fullMsg = (MessagesType) MessageJaxb.getValue();
            
            List<MessageType> chatMessage = fullMsg.getMessage();
            

            for (MessageType msg : chatMessage) {
                System.out.println("Message from " + msg.getFrom());
                System.out.println("Message To " + msg.getTo());
                System.out.println("Message body " + msg.getBody());
                System.out.println("Message date " + msg.getDate());
                System.out.println("Message color " + msg.getColor());
                System.out.println("Message fontfamily " + msg.getFont().getFontFamily());
                System.out.println("Message fontsize " + msg.getFont().getFontSize());
                System.out.println("Message fonttype " + msg.getFont().getFontType());
                System.out.println("----------------------------");

            }
            //JAXBElement msgElement = factory.createMessages(fullMsg);
            //Marshaller marsh = context.createMarshaller();
            //marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //marsh.marshal(msgElement, new FileOutputStream("src/main/java/com/itico/xmlchat/output.xml"));

        } catch (JAXBException ex) {
            Logger.getLogger(ReadingXml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        
        //Just For Test
        MessageType testMsg = new MessageType();
        testMsg.setFrom("Ahmed");
        testMsg.setTo("Mohamed");
        testMsg.setBody("This is For Just testing The Funtion of the Write Method");
        testMsg.setDate("11/2/2018");
        testMsg.setColor("Blue");
        FontType font =new FontType();
        font.setFontFamily("Ariel");
        font.setFontSize("61");
        font.setFontType("Italic");
        testMsg.setFont(font);
        Write(testMsg);
        //readXml();
        
        
        
    }

}
