package core.september.android.limboo.abs;

/**
 * Created by christian on 19/02/14.
 */

import android.app.Application;

public abstract class AbsApp extends Application {

    protected static AbsApp sInstance;

    //private SessionHandler sessionHandler;
    //private SessionHandler sessionHandler;
    public static AbsApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initialize();
    }

    protected abstract void initialize();
}
