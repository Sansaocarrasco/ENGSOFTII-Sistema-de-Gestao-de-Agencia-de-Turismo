package br.edu.univasf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Iniciando o programa com a tela de Login
        stage = primaryStage;
        switchScreen("login");
    }

    //Para carregar qualquer tela
    public static void switchScreen(String fxmlFile) {
        try {
            // Carregar o arquivo FXML baseado no nome
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/" + fxmlFile + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("Travel Manager");
            stage.setScene(scene); // Troca a cena para a nova tela
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função principal que inicia o aplicativo
    public static void main(String[] args) {
        launch(args);
    }
}
