package lucasdutracoelho.templatemobile.DAO;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

import lucasdutracoelho.templatemobile.DAO.Interface.GenericDao;
import lucasdutracoelho.templatemobile.DAO.Utils.DatabaseHelper;
import lucasdutracoelho.templatemobile.Model.Entidade;
import lucasdutracoelho.templatemobile.R;

/**
 * Created by lucas.coelho.dutra on 29/02/2016.
 */
public abstract class BaseDAO<T extends Entidade, ID> implements GenericDao<T, ID> {
    /**
     * Logging
     */
    private static final String TAG = BaseDAO.class.getSimpleName();

    protected Context context;
    protected Dao<T, ID> dao;
    private DatabaseHelper<T, ID> databaseHelper;

    public BaseDAO(final Class<T> clazz, final Context context){
        try {
            this.context = context;
            dao = getDatabaseHelper().getDao(clazz);
        } catch (SQLException e) {
            throw new RuntimeException(context.getString(R.string.msg_erro_create_dao));
        }
    }

    protected DatabaseHelper<T, ID> getDatabaseHelper() {
        //DatabaseHelper<T, ID> databaseHelper = (DatabaseHelper<T, ID>) OpenHelperManager.getHelper(context, DatabaseHelper.class);
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper<T, ID>(context);
        }
        return databaseHelper;
    }

    protected Dao<T, ID> getConnection() {
        return dao;
    }


    /**
     * @Override
     */
    @Override
    public T findById(ID id) {
        T result = null;
        try {
            result = dao.queryForId(id);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return result;
    }

    /**
     * @Override
     */
    @Override
    public boolean contains(ID id) {
        try {
            return dao.idExists(id);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return false;
    }

    /**
     * @Override
     */
    @Override
    public List<T> findAll() {
        List<T> results = null;
        try {
            results = dao.queryForAll();
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return results;
    }

    /**
     * @Override
     */
    @Override
    public T save(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return entity;
    }

    /**
     * @Override
     */
    @Override
    public T update(T entity) {
        try {
            dao.update(entity);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return entity;
    }

    /**
     * @Override
     */
    @Override
    public void delete(T entity) {
        int result = 0;
        try {
            result = dao.delete(entity);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        Log.d(TAG, result + " records are deleted!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int refresh(T entity) {
        int result = -1;
        try {
            result = dao.refresh(entity);
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long count() {
        Long result = 0L;
        try {
            result = dao.countOf();
        } catch (SQLException e) {
            throwFatalException(e);
        }
        return result;
    }

    @Override
    public void deleteAll() {
        DeleteBuilder<T, ID> deleteBuilder = dao.deleteBuilder();
        try {
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            throwFatalException(e);
        }
    }

    /**
     * Handles the throwing of fatal exceptions during basic SQL commands.
     * @param e The exception.
     */
    protected void throwFatalException(SQLException e) {
        String message = context.getString(R.string.msg_erro_sql_unknown);
        Log.e(TAG, message, e);
        throw new RuntimeException(message, e);
    }

}