package com.example.wachadlo;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private AnchorPane stx;
    @FXML
    private StackPane itx;

    private double mouseX, mouseY;
    private double ROTATE_ANGLE = 0.0;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itx.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();

            System.out.println(mouseX + mouseY);
        });

        itx.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;

            itx.setRotate(itx.getRotate() - deltaX);
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });


        itx.setOnMouseReleased(event -> {
            ROTATE_ANGLE = itx.getRotate();

            if (ROTATE_ANGLE > 0)
                ROTATE_ANGLE = -ROTATE_ANGLE;
            else
               ROTATE_ANGLE = Math.abs(ROTATE_ANGLE);

//            System.out.println("Po przejs" + ROTATE_ANGLE + "    po prasowaniu " + Math.abs((ROTATE_ANGLE - (ROTATE_ANGLE * 0.1))));
            if (ROTATE_ANGLE < 0)
                ROTATE_ANGLE = -Math.abs((ROTATE_ANGLE - (ROTATE_ANGLE * 0.1)));
            else
                ROTATE_ANGLE = Math.abs((ROTATE_ANGLE - (ROTATE_ANGLE * 0.1)));

            System.out.println(ROTATE_ANGLE);
            setAni();
        });
    }


    public void setAni() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1.5), itx);
        rotateTransition.setByAngle(ROTATE_ANGLE * 2);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);

        rotateTransition.play();

        if (ROTATE_ANGLE > 1 ||  ROTATE_ANGLE < -1) {
            if (ROTATE_ANGLE < 0)
                ROTATE_ANGLE = Math.abs((ROTATE_ANGLE - (ROTATE_ANGLE * 0.25)));
            else
                ROTATE_ANGLE = -Math.abs((ROTATE_ANGLE - (ROTATE_ANGLE * 0.25)));
            go();
        }
    }

    public void go() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setAni();
            }
        }, 1500);
    }
}