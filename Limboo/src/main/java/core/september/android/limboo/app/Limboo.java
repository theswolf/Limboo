package core.september.android.limboo.app;

import android.app.Application;

import com.quickblox.core.QBSettings;

import core.september.android.limboo.constants.Const;
import core.september.android.limboo.helpers.DaoHelper;
import core.september.android.limboo.models.AppUser;

/**
 * Created by christian on 19/02/14.
 */
public class Limboo extends Application {


    private AppUser appUser;

    protected static Limboo sInstance;

    //private SessionHandler sessionHandler;
    //private SessionHandler sessionHandler;
    public static Limboo getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initialize();
    }


    protected void initialize() {

        QBSettings.getInstance().fastConfigInit(Const.APPLICATION_ID, Const.AUTHORIZATION_KEY, Const.AUTHORIZATION_SECRET);
/*        QBAuth.createSession(new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    // You have successfully created the session
                    //
                    // Now you can use QuickBlox API!
                }
            }
        });*/

        //startService(new Intent(this, LimbooMainService.class));
    }

    public AppUser getAppUser() {
        if (appUser == null) {
            appUser = DaoHelper.getAppUser();
        }
        return appUser;
    }

    public void setAppUser(AppUser user) {
        DaoHelper.setAppUser(user);
    }

}
