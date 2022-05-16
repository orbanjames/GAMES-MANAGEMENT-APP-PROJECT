package com.jamesorban.gamesmanagementapp.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.jamesorban.gamesmanagementapp.database.dbutils.DBManager;
import com.jamesorban.gamesmanagementapp.database.models.BaseModel;
import com.jamesorban.gamesmanagementapp.utils.FxmlUtils;
import com.jamesorban.gamesmanagementapp.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class GeneralDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralDao.class);
    protected final ConnectionSource connectionSource;

    public GeneralDao() {
        this.connectionSource = DBManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create.update"));
        } finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.refresh"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T,I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T,I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    public void closeDBConnection() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
