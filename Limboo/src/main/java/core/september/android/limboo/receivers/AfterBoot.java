package core.september.android.limboo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import core.september.android.limboo.services.OnBootService;

/**
 * Created by christian on 19/02/14.
 */
public class AfterBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, OnBootService.class));
    }
}
