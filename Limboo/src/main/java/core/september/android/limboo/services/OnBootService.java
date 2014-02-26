package core.september.android.limboo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by christian on 19/02/14.
 */
public class OnBootService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
}
