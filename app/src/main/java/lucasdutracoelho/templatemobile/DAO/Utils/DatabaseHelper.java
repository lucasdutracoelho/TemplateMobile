package lucasdutracoelho.templatemobile.DAO.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;



import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas.coelho.dutra on 29/02/2016.
 */
/**
 * A utility class to be used to setup and interact with a database.
 * @param <T> Entity.
 * @param <ID> ID type.
 */
public class DatabaseHelper<T, ID> extends OrmLiteSqliteOpenHelper {

    private Map<Class<T>, Dao<T, ID>> daoCache = new HashMap<Class<T>, Dao<T, ID>>();
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DaoConstants.DATABASE, null, DaoConstants.VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate Database");

            for(Tables table : Tables.values()) {
                TableUtils.createTable(connectionSource, table.getTableClass());
            }

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for(Tables table : Tables.values()) {
                TableUtils.dropTable(connectionSource, table.getTableClass(), true);
            }
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }

    }

    public Dao<T, ID>  getDao(Class clazz) throws SQLException {
        if(daoCache.containsKey(clazz)){
            return daoCache.get(clazz);
        }
        daoCache.put(clazz, (Dao<T,ID>)super.getDao(clazz));
        return daoCache.get(clazz);
    }

}
