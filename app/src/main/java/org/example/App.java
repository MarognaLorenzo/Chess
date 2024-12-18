/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = stage;
        TextArea textArea = new TextArea("Content of text area");
        Button actionButton = new Button("Title of the button");
        TextField textField = new TextField();
        VBox vBox = new VBox (actionButton, textField, textArea);

        HBox bigHorizontalBox= new HBox(vBox);
        Scene scene = new Scene(bigHorizontalBox);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Chess Official Game - by Lorenzo Marogna");
        window.show();
    }
}
