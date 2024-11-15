package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Opcoes extends Application {
    public static Stage stage;// Minha janela

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Opcoes.class.getResource("view/opcoes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Travel Manager");
        stage.setScene(scene);
        stage.show();
        setStage(stage);// Setando a minha janela
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Opcoes.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}