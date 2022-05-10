package com.jamesorban.mygamestore.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jamesorban.mygamestore.database.models.*;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.sql.SQLException;


public class DBManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:mysql://localhost:3306/GamesDB";
    private static final String USER = "root";
    private static final String PASS = "123456@/";

    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
        dropTable(); //comment out, to delete tables in the database every time
        createTable();
        closeConnectionSource();
    }

    public static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Author.class);
            TableUtils.createTableIfNotExists(connectionSource, Game.class);
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private  static  void  dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Author.class, true);
            TableUtils.dropTable(connectionSource, Game.class, true);
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Product.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}
