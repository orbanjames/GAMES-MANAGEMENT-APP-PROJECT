package com.jamesorban.mygamestore.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.jamesorban.mygamestore.database.models.Game;
import com.jamesorban.mygamestore.database.models.Product;
import com.jamesorban.mygamestore.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class GameDao extends CommonDao {

    public GameDao( ) {
        super();
    }

    public List<Game> findAllByColumnName(String columnName, int id) throws ApplicationException, SQLException {
        Dao<Game, Object> dao = getDao(Game.class);
        QueryBuilder<Game, Object> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(columnName, id);
        PreparedQuery<Game> prepare = queryBuilder.prepare();
        return dao.query(prepare);
    }

    public void deleteByColumnName(String columnName, int id) throws ApplicationException, SQLException {
        Dao<Game, Object> dao = getDao(Game.class);
        DeleteBuilder<Game, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columnName, id);
        dao.delete(deleteBuilder.prepare());
    }
}
