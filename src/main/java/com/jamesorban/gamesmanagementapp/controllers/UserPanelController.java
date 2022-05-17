package com.jamesorban.gamesmanagementapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jamesorban.gamesmanagementapp.database.models.User;
import com.jamesorban.gamesmanagementapp.modelFx.UserPanelModel;

public class UserPanelController {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthLabel;

    private UserPanelModel userPanelModel;

    @FXML
    public void initialize(){
        this.userPanelModel = new UserPanelModel();

    }

    public void setUser(User user){
        this.userNameLabel.setText(user.getUserName());
        this.emailLabel.setText(user.getEmail());
        this.birthLabel.setText(user.getBirthDay().getDay() + "/" + user.getBirthDay().getMonth() + "/" + (1900 + user.getBirthDay().getYear()));
    }
}

