package in.thegeekybaniya.polyglot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Kabir on 13/04/2017.
 */

public class BootReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, ClipboardMonitor.class);
        context.startService(myIntent);



    }
}
