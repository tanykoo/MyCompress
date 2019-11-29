package com.tanykoo.view.pww.controller;

import com.tanykoo.fxml.Init;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author ThinkPad
 * Created : 2019-11-26 19:54
 * @since
 */
@Component
public class MainCtrl {

    @FXML
    TextField choser;
    @FXML
    TextField destchoser;
    @FXML
    GridPane rootLayout;

    @Init
    public void init(){
        choser.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
//                System.out.println(event.getEventType().getName());;
//                System.out.println(event.getDragboard().getFiles().get(0).getAbsolutePath());
                choser.setText(event.getDragboard().getFiles().get(0).getAbsolutePath());
            }
        });
//        choser.setOnDragDropped(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                System.out.println(event.getDragboard().getFiles().get(0).getAbsolutePath());
//                choser.setText(event.getDragboard().getFiles().get(0).getName());
//            }
//        });
        choser.addEventHandler(DragEvent.ANY, new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if(event.getEventType() != DragEvent.DRAG_OVER) {
                    event.getDragboard().getFiles();

                    System.out.println(event.getEventType().getName());
                }
            }
        });
    }

    public void chooser(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择解压文件");
        File file = fileChooser.showOpenDialog(rootLayout.getScene().getWindow());
        if(file != null) {
            System.out.println(file.getName());
            choser.setText(file.getAbsolutePath());
        }
    }

    public void destchooser(){
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("请选择解压目录");
        File file = fileChooser.showDialog(rootLayout.getScene().getWindow());
        if(file != null) {
            System.out.println(file.getName());
            destchoser.setText(file.getAbsolutePath());
        }
    }

    public void compress(ActionEvent event) {
        if("".equals(choser.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING,"请选择解压文件");
            alert.show();
        }
        System.out.println("解压完成");
    }
}
