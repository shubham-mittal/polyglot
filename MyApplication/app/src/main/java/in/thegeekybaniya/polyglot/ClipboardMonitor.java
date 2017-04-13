package in.thegeekybaniya.polyglot;

import android.app.IntentService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;

/**
 * Created by Kabir on 13/04/2017.
 */

public class ClipboardMonitor extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     *
     *
     */




    private ClipboardManager mClipboardManager;
    private ExecutorService mThreadPool = Executors.newSingleThreadExecutor();



    @Override
    public void onCreate() {
        super.onCreate();

        mClipboardManager =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(
                mOnPrimaryClipChangedListener);

    }

    private ClipboardManager.OnPrimaryClipChangedListener mOnPrimaryClipChangedListener =
            new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {

                    Intent myIntent = new Intent(getApplicationContext(), FloatingWindow.class);
                    startService(myIntent);

                    Toast.makeText(ClipboardMonitor.this, "CHALJA HAIIIIIIIII", Toast.LENGTH_SHORT).show();


                    Log.d(TAG, "onPrimaryClipChanged");
                    ClipData clip = mClipboardManager.getPrimaryClip();


                    Log.d(TAG, "onPrimaryClipChanged: "+ clip.getItemAt(0).getText());

                    Toast.makeText(ClipboardMonitor.this, clip.getItemAt(0).getText(), Toast.LENGTH_SHORT).show();


//                    mThreadPool.execute(new WriteHistoryRunnable(
//                            clip.getItemAt(0).getText()));
//                }
            };
    };

    public ClipboardMonitor(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        Log.d(TAG, "CHAL GAYI BHAI MONITOR SERVICE");

        Toast.makeText(this, "CHAL GAYI BHAI MONITOR SERVICE", Toast.LENGTH_SHORT).show();


    }
}
