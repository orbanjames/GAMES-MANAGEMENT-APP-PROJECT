package com.jamesorban.gamesmanagementapp.App;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.jamesorban.gamesmanagementapp.database.dbutils.DBManager;
import com.jamesorban.gamesmanagementapp.utils.FillDatabase;
import com.jamesorban.gamesmanagementapp.utils.FxmlUtils;


public class Application extends javafx.application.Application {

    public static final String APPLICATION_FXML = "/fxml/Application.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane borderPane = FxmlUtils.fxmlLoader(APPLICATION_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.setResizable(false);
        primaryStage.show();


        DBManager.initDatabase();
        FillDatabase.fillDatabase();
    }
}
