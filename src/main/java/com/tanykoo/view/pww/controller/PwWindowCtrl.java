package com.tanykoo.view.pww.controller;


import com.tanykoo.fxml.Init;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author ThinkPad
 * Created : 2018-12-26 13:30
 * @Since
 */
@Component
public class PwWindowCtrl {
    private static Log log = LogFactory.getLog(PwWindowCtrl.class);

    @FXML
    SplitPane spilt;
    @FXML
    Button but;
    @FXML
    ComboBox chose;


    public void hello(){
        log.info("hello world");
        TextField t = chose.getEditor();
        log.info(t.getPadding().getBottom());
        log.info(t.getPadding().getTop());
        log.info(t.getPadding().getLeft());
        log.info(t.getPadding().getRight());
        log.info(t.getBackground().getFills().size());
        BackgroundFill [] backgroundFills = new BackgroundFill[t.getBackground().getFills().size() ];
        for(int i = 0 ; i < backgroundFills.length;i++){
            backgroundFills[i] = t.getBackground().getFills().get(i);
        }
//        backgroundFills[backgroundFills.length-1] =  new BackgroundFill(new ImagePattern(new Image("/image/11.png")),new CornerRadii(0,false),new Insets(4,0,4,5));
        t.setBackground(new Background(backgroundFills,new BackgroundImage[]{new BackgroundImage(new Image("/image/11.png"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,null,null)}));
    }


    @Init
    private void init(){
        log.info(spilt);
        spilt.setOnSwipeLeft(event -> {log.info("hello1");});
        spilt.setOnDragDone(event -> {log.info("hello2");});
        List<SplitPane.Divider> list =  spilt.getDividers();
        TextField t = chose.getEditor();
//        t.setStyle("-fx-background-image: url('/image/11.png') ; -fx-background-repeat: no-repeat; -fx-background-position: 3,10;");
//        t.setPadding(new Insets(4,4,7,25));
//        but.setGraphic(new ImageView(new Image("/image/11.png")));

//        t.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("/image/11.png")),new CornerRadii(0,false),new Insets(4,0,4,5))));
    }

    public Image getIconByFileName(String filename){
        try {
            File file = File.createTempFile("icon", "."+filename );
            Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
//            if(icon instanceof ImageIcon){
//                return ((ImageIcon) icon).getImage();
//            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
