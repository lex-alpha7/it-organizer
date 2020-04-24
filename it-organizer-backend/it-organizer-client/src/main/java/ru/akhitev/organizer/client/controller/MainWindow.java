package ru.akhitev.organizer.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;

public class MainWindow {
    @FXML private Button test;

    @FXML
    public void initialize() {
        test.setOnMouseClicked(event -> {
            File file = new File("/home/aleksei_khitev/itoc_test.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
