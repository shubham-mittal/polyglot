package in.thegeekybaniya.polyglot;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import in.thegeekybaniya.polyglot.POJO.GetTranslate;
import in.thegeekybaniya.polyglot.POJO.Translation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit, retrofit2;


    Button btn;

    FirebaseDatabase mRoot=FirebaseDatabase.getInstance();

    DatabaseReference mData;

    EditText et;

    TextView tv;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= (EditText) findViewById(R.id.editText);

        tv= (TextView) findViewById(R.id.textView6);


        btn= (Button) findViewById(R.id.button);

        Intent myIntent = new Intent(MainActivity.this, ClipboardMonitor.class);
        startService(myIntent);

        mData=mRoot.getReference().child("notes");

        lv= (ListView) findViewById(R.id.lv);


        ListAdapter adapter= new FirebaseListAdapter<Translation>(this,   Translation.class,R.layout.mdata,mData)
        {

            @Override
            protected void populateView(View v, Translation model, int position) {
                ((TextView)v.findViewById(R.id.textView7)).setText(model.getTrans());
                ((TextView)v.findViewById(R.id.textView5)).setText(model.getOrig());
            }
        };

        lv.setAdapter(adapter);


        final ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener( new ClipboardManager.OnPrimaryClipChangedListener() {
            public void onPrimaryClipChanged() {
                final String a = clipboard.getText().toString();
                final String SAME = a ;
                Toast.makeText(getBaseContext(),"Copy:\n"+a,Toast.LENGTH_LONG).show();
                final Intent myIntent = new Intent(MainActivity.this, FloatingWindow.class);
//                startService(myIntent);
                retrofit= new Retrofit.Builder()
                        .baseUrl("https://translate.yandex.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final ApiInterface service= retrofit.create(ApiInterface.class);

                Call<GetTranslate> result=service.getLang("trnsl.1.1.20170412T152446Z.640993651b8fd9d5.06e9cd2efa56ab30c13bf05c6e2f160b410acfb4", URLEncoder.encode(a),"hi", "plain",1);


                result.enqueue(new Callback<GetTranslate>() {
                    public static final String TAG = "TRansaction";

                    @Override
                    public void onResponse(Call<GetTranslate> call, Response<GetTranslate> response) {

                        List<String> l= response.body().getText();


                        try {
                            Log.d(TAG, "onResponse: "+ URLDecoder.decode(l.get(0), "UTF-8"));
                            Toast.makeText(MainActivity.this, "Translation:"+URLDecoder.decode(l.get(0), "UTF-8") , Toast.LENGTH_LONG).show();

//                            myIntent.putExtra("original",a );a

                            if (!a.equals(URLDecoder.decode(l.get(0), "UTF-8"))){

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("original",a);

                                Log.d(TAG, "onResponse: ");

                                editor.putString("translated",URLDecoder.decode(l.get(0), "UTF-8"));
                                editor.commit() ;
                                startService(myIntent);

                            }











                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTranslate> call, Throwable t) {

                    }
                });





            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit2= new Retrofit.Builder()
                        .baseUrl("https://translate.yandex.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final ApiInterface service= retrofit2.create(ApiInterface.class);

                Call<GetTranslate> result=service.getLang("trnsl.1.1.20170412T152446Z.640993651b8fd9d5.06e9cd2efa56ab30c13bf05c6e2f160b410acfb4", URLEncoder.encode(et.getText().toString()),"hi", "plain",1);



                result.enqueue(new Callback<GetTranslate>() {
                    @Override
                    public void onResponse(Call<GetTranslate> call, Response<GetTranslate> response) {

                        List<String> l= response.body().getText();

                        tv.setText(l.get(0));

                    }

                    @Override
                    public void onFailure(Call<GetTranslate> call, Throwable t) {

                    }
                });

            }
        });




    }
}
