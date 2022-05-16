package com.jamesorban.gamesmanagementapp.utils.converters;

import com.jamesorban.gamesmanagementapp.database.models.User;
import com.jamesorban.gamesmanagementapp.modelFx.UserFx;
import com.jamesorban.gamesmanagementapp.utils.Utils;

public class ConverterUser {

    public static User convertToUser(UserFx userFx){
        User user = new User();
        user.setUserName(userFx.getUserName());
        user.setEmail(userFx.getEmail());
        user.setPassword(userFx.getPassword());
        user.setLanguage(userFx.getLanguage());
        user.setBirthDay(Utils.convertToDate(userFx.getBirthDay()));
        return user;
    }

    public  static UserFx convertToUserFx(User user){
        UserFx userFx = new UserFx();
        userFx.setUserName(user.getUserName());
        userFx.setEmail(user.getEmail());
        userFx.setPassword(user.getPassword());
        userFx.setLanguage(user.getLanguage());
        userFx.setBirthDay(Utils.convertToLocalDate(user.getBirthDay()));
        return userFx;
    }
}

