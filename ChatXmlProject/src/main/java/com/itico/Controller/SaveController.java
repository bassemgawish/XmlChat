/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itico.Controller;

import com.itico.classes.WriteXml;
import com.itico.generatedXmlClasses.FontType;
import com.itico.generatedXmlClasses.MessageType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mariam
 */
public class SaveController {

    @FXML
    private Button saveBtn;

    @FXML
    void saveFile(ActionEvent event) {
        
         MessageType testMsg = new MessageType();
        testMsg.setFrom("Ahmed@gmail.com");
        testMsg.setTo("Mohamed@gmail.com");
        testMsg.setBody("This is gui testtttttttttt Just testing The Funtion of the Write Method");
        testMsg.setDate("11/2/2018");
        testMsg.setColor("Blue");
        FontType font =new FontType();
        font.setFontFamily("Ariel");
        font.setFontSize("61");
        font.setFontType("Italic");
        testMsg.setFont(font);
        List<MessageType> messageList = new ArrayList<>();
        messageList.add(testMsg);
        messageList.add(testMsg);
        WriteXml xmlwrite = new WriteXml();
      
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save Resource File");
        
        File fileChoosen = fileChooser.showSaveDialog(saveBtn.getScene().getWindow());
        xmlwrite.Write(messageList ,fileChoosen , "Ahmed@gmail.com");
//        System.out.println("file name :  " + fileChoosen.getAbsolutePath());
//        System.out.println("file name :  " + fileChoosen.getParent());
//        System.out.println("file parent :  " + fileChoosen.getParentFile().getAbsolutePath());
        
    }

}
