package lucasdutracoelho.templatemobile.Application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if(instance == null){
            instance = this;
        }
        Fresco.initialize(this);
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
