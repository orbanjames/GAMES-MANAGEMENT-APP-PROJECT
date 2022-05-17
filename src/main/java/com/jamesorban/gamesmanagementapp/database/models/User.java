package com.jamesorban.gamesmanagementapp.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "USERS")
public class User implements BaseModel {

    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    @DatabaseField(generatedId = true)
    private int id;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Product> products;

    @DatabaseField(columnName = USERNAME, canBeNull = false, unique = true)
    private String userName;

    @DatabaseField(columnName = EMAIL, canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;

    @DatabaseField(columnName = "LANGUAGE", canBeNull = false)
    private String language;

    @DatabaseField(columnName = "BIRTH_DAY")
    private Date birthDay;


    public User() {
    }

    public User(String userName, String email, String password) {
    }


    public int getId() {
        return id;
    }

    public ForeignCollection<Product> getProducts() {
        return products;
    }

    public void setProducts(ForeignCollection<Product> products) {
        this.products = products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", products=" + products +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", language='" + language + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
