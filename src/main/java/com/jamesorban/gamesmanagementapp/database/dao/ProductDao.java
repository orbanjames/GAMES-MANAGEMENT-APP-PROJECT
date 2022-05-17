package com.jamesorban.gamesmanagementapp.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jamesorban.gamesmanagementapp.database.models.Product;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class ProductDao extends GeneralDao {

    public ProductDao() {
        super();
    }

    public List<Product> findAllByUserId(int id) throws ApplicationException, SQLException {
        Dao<Product, Object> dao = getDao(Product.class);
        QueryBuilder<Product, Object> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("USER_ID", id);
        PreparedQuery<Product> prepare = queryBuilder.prepare();
        return dao.query(prepare);
    }

    public void deleteByColumnName(String columnName, int id) throws ApplicationException, SQLException {
        Dao<Product, Object> dao = getDao(Product.class);
        DeleteBuilder<Product, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columnName, id);
        dao.delete(deleteBuilder.prepare());
    }

}
