module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens br.edu.univasf to javafx.fxml;
    exports br.edu.univasf;

    opens br.edu.univasf.controller to javafx.fxml;
    exports br.edu.univasf.controller;
}