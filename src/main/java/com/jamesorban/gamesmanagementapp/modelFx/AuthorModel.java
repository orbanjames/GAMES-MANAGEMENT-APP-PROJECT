package com.jamesorban.gamesmanagementapp.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jamesorban.gamesmanagementapp.database.dao.AuthorDao;
import com.jamesorban.gamesmanagementapp.database.dao.GameDao;
import com.jamesorban.gamesmanagementapp.database.dao.ProductDao;
import com.jamesorban.gamesmanagementapp.database.models.Author;
import com.jamesorban.gamesmanagementapp.database.models.Game;
import com.jamesorban.gamesmanagementapp.database.models.Product;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterAuthor;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorObjectProperty = new SimpleObjectProperty<>(new AuthorFx());
    private ObjectProperty<AuthorFx> authorObjectPropertyEdit = new SimpleObjectProperty<>(new AuthorFx());
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authors = authorDao.queryForAll(Author.class);
        this.authorFxObservableList.clear();
        authors.forEach(author -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(author);
            this.authorFxObservableList.add(authorFx);
        });
    }

    public void saveAuthorEditInDatabase() throws ApplicationException {
        saveOrUpdateAuthor(this.getAuthorObjectPropertyEdit());
    }

    public void saveAuthorInDatabase() throws ApplicationException {
        saveOrUpdateAuthor(this.getAuthorObjectProperty());
    }

    private void saveOrUpdateAuthor(AuthorFx authorFxObjectPropertyEdit) throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        Author author = ConverterAuthor.convertToAuthor(authorFxObjectPropertyEdit);
        authorDao.creatOrUpdate(author);

        init();
    }

    public void deleteAuthorInDataBase() throws ApplicationException, SQLException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.deleteById(Author.class, this.getAuthorObjectPropertyEdit().getId());

        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.findAllByColumnName(Game.AUTHOR_ID, this.getAuthorObjectPropertyEdit().getId());

        ProductDao productDao = new ProductDao();
        for(Game game : games) {
            productDao.deleteByColumnName(Product.GAME_ID, game.getId());
        }

        //GameDao gameDao = new GameDao();
        gameDao.deleteByColumnName(Game.AUTHOR_ID, this.getAuthorObjectPropertyEdit().getId());
        this.init();
    }

    public AuthorFx getAuthorObjectProperty() {
        return authorObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorObjectPropertyProperty() {
        return authorObjectProperty;
    }

    public void setAuthorObjectProperty(AuthorFx authorObjectProperty) {
        this.authorObjectProperty.set(authorObjectProperty);
    }

    public AuthorFx getAuthorObjectPropertyEdit() {
        return authorObjectPropertyEdit.get();
    }

    public ObjectProperty<AuthorFx> authorObjectPropertyEditProperty() {
        return authorObjectPropertyEdit;
    }

    public void setAuthorObjectPropertyEdit(AuthorFx authorObjectPropertyEdit) {
        this.authorObjectPropertyEdit.set(authorObjectPropertyEdit);
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

}
