package com.jamesorban.gamesmanagementapp.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jamesorban.gamesmanagementapp.database.dao.GameDao;
import com.jamesorban.gamesmanagementapp.database.dao.ProductDao;
import com.jamesorban.gamesmanagementapp.database.models.Game;
import com.jamesorban.gamesmanagementapp.database.models.Product;
import com.jamesorban.gamesmanagementapp.database.models.User;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterGame;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterProduct;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProductModel {

    private ObservableList<GameFx> gameFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<ProductFx> productFxObjectProperty = new SimpleObjectProperty<>(new ProductFx());

    private User user;


    public void init(User user) throws ApplicationException, SQLException {
        this.user = user;

        initGameList();
    }

    private void initGameList() throws ApplicationException, SQLException {
        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.queryForAll(Game.class);

        ProductDao productDao = new ProductDao();
        List<Product> myProduct = productDao.findAllByUserId(this.user.getId());

        games.forEach(c -> {
            AtomicReference<Boolean> check = new AtomicReference<>(true);
            myProduct.forEach(b -> {
                if(c.getId() == b.getGame().getId()){
                    check.set(false);
                }
            });

            if (check.get()) {
                this.gameFxObservableList.add(ConverterGame.convertToGameFx(c));
            }
        });
    }

    public void saveProductInDataBase() throws ApplicationException {
        Product product = ConverterProduct.convertToProduct(this.getProductFxObjectProperty());

        GameDao gameDao = new GameDao();
        Game game = gameDao.findById(Game.class, this.getProductFxObjectProperty().getGameFx().getId());

        product.setUser(this.user);
        product.setGame(game);

        ProductDao productDao = new ProductDao();
        productDao.creatOrUpdate(product);
    }


    public ObservableList<GameFx> getGameFxObservableList() {
        return gameFxObservableList;
    }

    public void setGameFxObservableList(ObservableList<GameFx> gameFxObservableList) {
        this.gameFxObservableList = gameFxObservableList;
    }

    public ProductFx getProductFxObjectProperty() {
        return productFxObjectProperty.get();
    }

    public ObjectProperty<ProductFx> productFxObjectPropertyProperty() {
        return productFxObjectProperty;
    }

    public void setProductFxObjectProperty(ProductFx productFxObjectProperty) {
        this.productFxObjectProperty.set(productFxObjectProperty);
    }
}
