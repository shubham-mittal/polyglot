package in.thegeekybaniya.polyglot;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * Created by Kabir on 13/04/2017.
 */

public class FloatingWindow extends Service {

    WindowManager wm;

    LinearLayout li;

    FrameLayout fl;


    TextView original, translated;

    String orig, trans;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        orig=intent.getStringExtra("original");
//
//        trans= intent.getStringExtra("translated");
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        orig=preferences.getString("original","null");
//        trans=preferences.getString("translated","null");
//
//        Log.d("TAG",trans);





        return super.onStartCommand(intent, flags, startId);
    }

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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        orig=preferences.getString("original","null");
        trans=preferences.getString("translated","null");

        Log.d("PIYUSH",trans);



//
//        original= (TextView) fl.findViewById(R.id.textView4);
//
//        translated= (TextView) fl.findViewById(R.id.textView);






        fl=new FrameLayout(this);









        li=new LinearLayout(this);

        LinearLayout.LayoutParams liParams=  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        li.setBackgroundColor(Color.argb(66,255,234,100));
        li.setLayoutParams(liParams);

        WindowManager.LayoutParams parameters=  new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        parameters.x=0;
        parameters.y=0;
        parameters.gravity= Gravity.CENTER| Gravity.CENTER;


        final LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.window_layout, fl, true);




        fl.setBackgroundColor(Color.BLUE);


        wm.addView(fl, parameters);


        original= (TextView) fl.findViewById(R.id.textView4);

        translated= (TextView) fl.findViewById(R.id.textView);





        original.setText(orig);

        translated.setText(trans);










        li.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Toast.makeText(FloatingWindow.this, "HAN BHA CHAL GAYA", Toast.LENGTH_SHORT).show();





                return false;
            }
        });
    }

   
}
