package in.thegeekybaniya.polyglot;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import in.thegeekybaniya.polyglot.POJO.Translation;

import static android.content.ContentValues.TAG;


/**
 * Created by Kabir on 13/04/2017.
 */

public class FloatingWindow extends Service {

    TextToSpeech t1;

    WindowManager wm;

    LinearLayout li;

    FrameLayout fl;


    TextView original, translated;

    String orig, trans;

    Button close, detail,speech;

    FirebaseDatabase mRoot=FirebaseDatabase.getInstance();

    DatabaseReference mData;


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
//        orig=intent.getStringExtra("original");
//
//        trans= intent.getStringExtra("translated");
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        orig=preferences.getString("original","null");
//        trans=preferences.getString("translated","null");
//
//        Log.d("TAG",trans);



        mData=mRoot.getReference().child("notes");






        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


        Toast.makeText(this, "SERVICE ENDED", Toast.LENGTH_SHORT).show();
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

        li.setLayoutParams(liParams);

        Drawable de = getResources().getDrawable(R.drawable.customborder) ;
        li.setBackground(de);
        li.setPadding(20,20,20,20);

        WindowManager.LayoutParams parameters=  new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.OPAQUE);
        parameters.x=0;
        parameters.y=0;
        parameters.gravity= Gravity.CENTER| Gravity.CENTER;


        final LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        layoutInflater.inflate(R.layout.window_layout, fl, true);




//        fl.setBackgroundColor(Color.WHITE);


        wm.addView(fl, parameters);


        original= (TextView) fl.findViewById(R.id.textView4);

        translated= (TextView) fl.findViewById(R.id.textView);
        close= (Button) fl.findViewById(R.id.button3);

        detail= (Button) fl.findViewById(R.id.button2);

        speech= (Button) fl.findViewById(R.id.speech);




        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wm.removeView(fl);

                stopService(new Intent(getApplicationContext(), FloatingWindow.this.getClass()));
            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.push().setValue(new Translation(orig,trans));

            }
        });
        
        
        
        





        original.setText(orig);

        translated.setText(trans);


        t1= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);

                }
            }
        });

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                t1.speak(orig, TextToSpeech.QUEUE_FLUSH, null);

            }
        });






        li.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Toast.makeText(FloatingWindow.this, "HAN BHA CHAL GAYA", Toast.LENGTH_SHORT).show();





                return false;
            }
        });
    }

   
}
