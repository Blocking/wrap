package com.example.wrap.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author 12232
 * @date 2018/1/29
 */
public class HelloWorldApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Hello World");
        label.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(label, 300, 250));
        primaryStage.setTitle("Hello World Application");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
