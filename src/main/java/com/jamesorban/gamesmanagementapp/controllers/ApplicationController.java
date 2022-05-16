package com.jamesorban.gamesmanagementapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import com.jamesorban.gamesmanagementapp.utils.DialogUtils;
import com.jamesorban.gamesmanagementapp.utils.FxmlUtils;

import java.io.IOException;
import java.util.Locale;


public class ApplicationController {

    private static final String MENU_APP_FXML = "/fxml/MenuApp.fxml";

    @FXML
    public StackPane mainStackPane;


    @FXML
    public void initialize(){
        setLanguage("ENGLISH");
        loadScreen();
    }

    public void loadScreen() {
        FXMLLoader loader = FxmlUtils.getLoader(MENU_APP_FXML);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        MenuAppController menuController = loader.getController();
        menuController.setApplicationController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    public void setLanguage(String language){
        Locale.setDefault(new Locale(language.toLowerCase()));
    }
}
