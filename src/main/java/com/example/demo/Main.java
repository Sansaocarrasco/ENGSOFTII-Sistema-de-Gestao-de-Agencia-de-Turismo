package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 300, 200); // Cria uma cena com largura 300 e altura 200

        primaryStage.setTitle("Test App"); // Título da janela
        primaryStage.setScene(scene); // Define a cena na janela
        primaryStage.show(); // Mostra a janela
    }

    public static void main(String[] args) {
        launch(args); // Chama o método launch para iniciar a aplicação
    }
}
