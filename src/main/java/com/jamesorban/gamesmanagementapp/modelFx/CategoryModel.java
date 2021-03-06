package com.jamesorban.gamesmanagementapp.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import com.jamesorban.gamesmanagementapp.database.dao.CategoryDao;
import com.jamesorban.gamesmanagementapp.database.dao.GameDao;
import com.jamesorban.gamesmanagementapp.database.dao.ProductDao;
import com.jamesorban.gamesmanagementapp.database.models.Category;
import com.jamesorban.gamesmanagementapp.database.models.Game;
import com.jamesorban.gamesmanagementapp.database.models.Product;
import com.jamesorban.gamesmanagementapp.utils.converters.ConverterCategory;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();
    private TreeItem<String> root = new TreeItem<>();


    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.queryForAll(Category.class);
        initCategoryList(categories);
        initRoot(categories);
    }

    private void initRoot(List<Category> categories) {
        this.root.getChildren().clear();
        categories.forEach(c->{
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getGames().forEach(b->{
                categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
            });
            root.getChildren().add(categoryItem);
        });
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c->{
            CategoryFx categoryFx = ConverterCategory.covertToCategoryFx(c);
            this.categoryList.add(categoryFx);
        });
    }

    public void deleteCategoryById() throws ApplicationException, SQLException {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.deleteById(Category.class, this.category.getValue().getId());

        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.findAllByColumnName(Game.CATEGORY_ID, this.getCategory().getId());

        ProductDao productDao = new ProductDao();
        for(Game game : games) {
            productDao.deleteByColumnName(Product.GAME_ID, game.getId());
        }

        gameDao.deleteByColumnName(Game.CATEGORY_ID, this.category.getValue().getId());
        init();
    }

    public void saveCategoryInDataBase(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        init();
    }

    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);

        init();
    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }

}
