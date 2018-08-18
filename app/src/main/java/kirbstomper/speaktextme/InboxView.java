package kirbstomper.speaktextme;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class InboxView extends AppCompatActivity {

    MessageInterceptor messageInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SpeakerService speakerService = SpeakerService.getSpeakerService(this.getApplicationContext());//Starts Speaker Service so that it is ready for messageInterceptor
        messageInterceptor = new MessageInterceptor();
        getApplicationContext().registerReceiver(messageInterceptor, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_view);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String out = "GRANTED";
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    out = "NOT GRANTED";
                }
                Snackbar.make(view, out, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox_view, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
