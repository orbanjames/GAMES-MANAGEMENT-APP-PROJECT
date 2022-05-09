package com.jamesorban.mygamestore.utils.converters;

import com.jamesorban.mygamestore.database.models.User;
import com.jamesorban.mygamestore.modelFx.UserFx;
import com.jamesorban.mygamestore.utils.Utils;

public class ConverterUser {

    public static User convertToUser(UserFx userFx){
        User user = new User();
        user.setNick(userFx.getNick());
        user.setEmail(userFx.getEmail());
        user.setPassword(userFx.getPassword());
        user.setLanguage(userFx.getLanguage());
        user.setBirthDay(Utils.convertToDate(userFx.getBirthDay()));
        return user;
    }

    public  static UserFx convertToUserFx(User user){
        UserFx userFx = new UserFx();
        userFx.setNick(user.getNick());
        userFx.setEmail(user.getEmail());
        userFx.setPassword(user.getPassword());
        userFx.setLanguage(user.getLanguage());
        userFx.setBirthDay(Utils.convertToLocalDate(user.getBirthDay()));
        return userFx;
    }
}

