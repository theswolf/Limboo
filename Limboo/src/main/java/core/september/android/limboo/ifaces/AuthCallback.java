package core.september.android.limboo.ifaces;

/**
 * Created by christian on 28/02/14.
 */
public interface AuthCallback {
    void onSessionSuccess();

    void onSessionError();

    void onAuthSuccess();

    void onAuthError();
}
