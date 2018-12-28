package com.tanykoo.bootstrap;

import com.tanykoo.fxml.LoadUtil;
import com.tanykoo.util.SpringUtils;
import com.tanykoo.view.pww.controller.PwWindowCtrl;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author ThinkPad
 * Created : 2018-12-26 13:32
 * @Since
 */
public class BootStrap extends Application {
    private static Log logger = LogFactory.getLog(BootStrap.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = null;
//        Dialog dialog = new Dialog();
//
//        dialog.show();

        try {
            parent = LoadUtil.load("/layout/pwWindow.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parameters parameters = getParameters();
        logger.info(parameters.getRaw());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);

//        primaryStage.setResizable(false);
        primaryStage.setTitle("输入密码");
        primaryStage.setOnCloseRequest(event -> {
                System.exit(0);
            }
        );

        PwWindowCtrl pwWindowCtrl = SpringUtils.getBean(PwWindowCtrl.class);
        primaryStage.show();

        pwWindowCtrl.hello();
    }
}
