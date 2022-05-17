package com.jamesorban.gamesmanagementapp.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jamesorban.gamesmanagementapp.database.dao.UserDao;
import com.jamesorban.gamesmanagementapp.database.models.User;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterUser;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignUpModel {

    private ObjectProperty<UserFx> userFxObjectProperty = new SimpleObjectProperty<>(new UserFx());
    private ObservableList<String> languages = FXCollections.observableArrayList();


    public void init(){
        this.languages.add("ENGLISH");
        this.languages.add("DE");
        this.languages.add("SERBIAN");
    }

    public Boolean checkUser(String columnName, String field){
        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<>();
        try {
            users =  userDao.findByColumnName(columnName, field);
        } catch (ApplicationException | SQLException e) {
            e.printStackTrace();
        }

        return !users.isEmpty();
    }

    public void saveUserInDataBase() throws ApplicationException {
        User user = ConverterUser.convertToUser(this.userFxObjectProperty.get());

        UserDao userDao = new UserDao();
        userDao.creatOrUpdate(user);
    }


    public UserFx getUserFxObjectProperty() {
        return userFxObjectProperty.get();
    }

    public ObjectProperty<UserFx> userFxObjectPropertyProperty() {
        return userFxObjectProperty;
    }

    public void setUserFxObjectProperty(UserFx userFxObjectProperty) {
        this.userFxObjectProperty.set(userFxObjectProperty);
    }

    public ObservableList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ObservableList<String> languages) {
        this.languages = languages;
    }


}
