package com.example.springwithfx.config;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class StageManager {

    @Autowired
    private SpringContext springContext;

    public void switchScene(Stage stage,Scene scene,FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setControllerFactory(this.springContext.getApplicationContext()::getBean);
        scene = new Scene(fxmlLoader.load(),1280,720);
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/frog.jpg")));
        String css =  Objects.requireNonNull(this.getClass().getResource("../application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.getIcons().add(icon);
        stage.setTitle("Pepe The Frog");
        stage.show();
    }

    public void switchScene(Stage stage, Scene scene, FXMLLoader fxmlLoader, MouseEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setControllerFactory(this.springContext.getApplicationContext()::getBean);
        scene = new Scene(fxmlLoader.load(),1280,720);
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/frog.jpg")));
        String css =  Objects.requireNonNull(this.getClass().getResource("../application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.getIcons().add(icon);
        stage.setTitle("Pepe The Frog");
        stage.show();
    }
}
