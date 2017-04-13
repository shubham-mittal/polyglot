package in.thegeekybaniya.polyglot;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn= (Button) findViewById(R.id.button);

        Intent myIntent = new Intent(MainActivity.this, ClipboardMonitor.class);
        startService(myIntent);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, FloatingWindow.class);
                startService(myIntent);





            }
        });


        final ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener( new ClipboardManager.OnPrimaryClipChangedListener() {
            public void onPrimaryClipChanged() {
                String a = clipboard.getText().toString();
                Toast.makeText(getBaseContext(),"Copy:\n"+a,Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(MainActivity.this, FloatingWindow.class);
                startService(myIntent);




            }
        });






    }
}
