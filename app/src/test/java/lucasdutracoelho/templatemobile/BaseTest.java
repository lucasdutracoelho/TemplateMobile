package lucasdutracoelho.templatemobile;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import lucasdutracoelho.templatemobile.DAO.BaseDAO;

/**
 * Created by lucas.coelho.dutra on 01/03/2016.
 */
public abstract class BaseTest {

    protected final String INSTANCE = "instance_";

    protected BaseDAO configureDao(Class calzz, Context context){
        try {

            Class[] param = new Class[1];
            param[0] = Context.class;
            Constructor c =  calzz.getDeclaredConstructors()[0];
            c.setAccessible(true);
            return (BaseDAO)c.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected Object returnInstance(Class clazz, Object... paramConstructor) {
        try {
            Constructor c = clazz.getDeclaredConstructors()[0];
            c.setAccessible(true);
            return c.newInstance(paramConstructor);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
