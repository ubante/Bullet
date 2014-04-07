package com.ubante.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class ScratchActivity extends Activity {
    private TextView tvCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);
        tvCountdown = (TextView) findViewById(R.id.textViewScratchCountdown);
        Parse.initialize(this, "49xRaGT7dNiwuCp5EEZjf9KRFoVtJ12aygBRNcEg", "zse7ogRQqfKEzNgqVNWzv9MIHKGe5a2xbAZhaK9v");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        Toast.makeText(this,"Parse test object saved maybe",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scratch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onScratch(View v) {
        Toast.makeText(this, "Scratching", Toast.LENGTH_SHORT).show();
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountdown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tvCountdown.setText("done!");
            }
        }.start();

    }

}
