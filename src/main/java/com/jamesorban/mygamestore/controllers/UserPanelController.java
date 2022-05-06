package com.jamesorban.mygamestore.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jamesorban.mygamestore.database.models.User;
import com.jamesorban.mygamestore.modelFx.UserPanelModel;
import com.jamesorban.mygamestore.utils.converters.ConverterUser;

public class UserPanelController {

    @FXML
    private Label nickLabel;
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
        this.nickLabel.setText(user.getNick());
        this.emailLabel.setText(user.getEmail());
        this.birthLabel.setText(user.getBirthDay().getDay() + "/" + user.getBirthDay().getMonth() + "/" + (1900 + user.getBirthDay().getYear()));
    }
}

