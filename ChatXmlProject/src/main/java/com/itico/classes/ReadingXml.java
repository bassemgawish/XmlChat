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
    
    
    public static void readXml(List<MessageType> chatMessage)
    {
        for(MessageType msg:chatMessage)
            {
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
        
    }
    
    public static void main(String[] args) {
        
        try {
            JAXBContext context = JAXBContext.newInstance("com.itico.generated");
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement MessageJaxb = (JAXBElement) unmarsh.unmarshal(new File("src/main/java/com/itico/xmlchat/MessageXml.xml"));
            //PersonType personType = (PersonType) JAXBPerson.getValue();
            MessagesType fullMsg = (MessagesType) MessageJaxb.getValue();
            List<MessageType> chatMessage = fullMsg.getMessage();
            readXml(chatMessage);
            
            ObjectFactory factory = new ObjectFactory();
            JAXBElement msgElement = factory.createMessages(fullMsg);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(msgElement, new FileOutputStream("output.xml"));
            
        } catch (JAXBException ex) {
            Logger.getLogger(ReadingXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadingXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    
}
