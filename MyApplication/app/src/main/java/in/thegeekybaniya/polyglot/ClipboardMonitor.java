package in.thegeekybaniya.polyglot;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Kabir on 13/04/2017.
 */

public class ClipboardMonitor extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ClipboardMonitor(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        Log.d(TAG, "CHAL GAYI BHAI MONITOR SERVICE");

        Toast.makeText(this, "CHAL GAYI BHAI MONITOR SERVICE", Toast.LENGTH_SHORT).show();


    }
}
