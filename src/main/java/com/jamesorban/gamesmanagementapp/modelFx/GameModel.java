package com.jamesorban.gamesmanagementapp.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jamesorban.gamesmanagementapp.database.dao.AuthorDao;
import com.jamesorban.gamesmanagementapp.database.dao.CategoryDao;
import com.jamesorban.gamesmanagementapp.database.dao.GameDao;
import com.jamesorban.gamesmanagementapp.database.models.Author;
import com.jamesorban.gamesmanagementapp.database.models.Category;
import com.jamesorban.gamesmanagementapp.database.models.Game;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterAuthor;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterCategory;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterGame;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.util.List;

public class GameModel {

    private ObjectProperty<GameFx> gameFxObjectProperty = new SimpleObjectProperty<>(new GameFx());
    private ObservableList<CategoryFx> categoryFxObservableList= FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException {
        initAuthorList();
        initCatogoryList();
    }

    private void initCatogoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);

        this.categoryFxObservableList.clear();
        categoryList.forEach(c -> {
            CategoryFx categoryFx = ConverterCategory.covertToCategoryFx(c);
            this.categoryFxObservableList.add(categoryFx);
        });

    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);

        this.authorFxObservableList.clear();
        authorList.forEach(c -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(c);
            this.authorFxObservableList.add(authorFx);
        });

    }

    public void saveGameInDataBase() throws ApplicationException {
        Game game = ConverterGame.convertToGame(this.getGameFxObjectProperty());

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getGameFxObjectProperty().getCategoryFx().getId());

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findById(Author.class, this.getGameFxObjectProperty().getAuthorFx().getId());

        game.setCategory(category);
        game.setAuthor(author);

        GameDao gameDao = new GameDao();
        gameDao.creatOrUpdate(game);

    }

    public GameFx getGameFxObjectProperty() {
        return gameFxObjectProperty.get();
    }

    public ObjectProperty<GameFx> gameFxObjectPropertyProperty() {
        return gameFxObjectProperty;
    }

    public void setGameFxObjectProperty(GameFx gameFxObjectProperty) {
        this.gameFxObjectProperty.set(gameFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
