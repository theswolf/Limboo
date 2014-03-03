package core.september.android.limboo.app;

import android.app.Activity;
import android.app.Application;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.model.QBUser;

import core.september.android.limboo.constants.Const;
import core.september.android.limboo.helpers.DaoHelper;
import core.september.android.limboo.ifaces.AuthCallback;
import core.september.android.limboo.models.AppUser;

/**
 * Created by christian on 19/02/14.
 */
public class Limboo extends Application {


    public AppUser appUser;
    public Activity runningActivity;

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


    public void doSigningProcedure(final AuthCallback callback) {
        QBUsers.signIn(appUser.getUserName(), appUser.getPassword(), new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                // result comes here
                // check if result success
                if (result.isSuccess()) {
                    callback.onAuthSuccess(result);
                } else {
                    callback.onAuthError(result);
                }
            }
        });
    }

    public void doSessionInit(final AuthCallback callback) {
        QBAuth.createSession(new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    callback.onSessionSuccess(result);
                } else {
                    callback.onSessionError(result);
                }


            }
        });
    }

	 /*StringBuilder builder = new StringBuilder();
                                for (String s : result.getErrors()) {
                                    builder.append(s);
                                    builder.append("/n");
                                }
                                Toast.makeText(activity, builder, Toast.LENGTH_LONG);*/

    public void doSignUpSignInProcedure(final AuthCallback callback) {
        QBUsers.signUpSignInTask(new QBUser(appUser.getUserName(), appUser.getPassword()), new QBCallbackImpl() {
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    callback.onAuthSuccess(result);
                } else {
                    callback.onAuthError(result);
                }


            }

        });

    }

    public void setAppUser(AppUser user) {
        appUser = user;
        DaoHelper.setAppUser(appUser);
    }
}
    
    

