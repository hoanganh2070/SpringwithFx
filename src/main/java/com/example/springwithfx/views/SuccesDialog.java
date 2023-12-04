package com.example.springwithfx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccesDialog {
    public SuccesDialog() throws IOException {
        Stage stage1 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/success.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 450, 100);
        stage1.setScene(scene1);
        stage1.show();
    }
}
