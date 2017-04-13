package in.thegeekybaniya.polyglot;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * Created by Kabir on 13/04/2017.
 */

public class FloatingWindow extends Service {

    WindowManager wm;

    LinearLayout li;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: STARTED");

        wm= (WindowManager) getSystemService(WINDOW_SERVICE);

        li=new LinearLayout(this);

        LinearLayout.LayoutParams liParams=  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        li.setBackgroundColor(Color.argb(66,255,234,100));
        li.setLayoutParams(liParams);

        WindowManager.LayoutParams parameters=  new WindowManager.LayoutParams(400, 150,WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        parameters.x=0;
        parameters.y=0;
        parameters.gravity= Gravity.CENTER| Gravity.CENTER;

        wm.addView(li, parameters);


        li.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Toast.makeText(FloatingWindow.this, "HAN BHA CHAL GAYA", Toast.LENGTH_SHORT).show();





                return false;
            }
        });
    }
}
