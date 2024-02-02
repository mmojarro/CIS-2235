// Programmer: Michael Mojarro
// Email: mmojarro@cnm.edu
// Program Name: Enigma Program
// File Name: MainApplication.java


package com.cis2235.mojarrop8.mojarrop8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EnigmaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnigmaApplication.class.getResource("Enigma-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("MojarroP8 Enigma Encoder and Decoder Machine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}