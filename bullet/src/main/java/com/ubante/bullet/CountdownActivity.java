package com.ubante.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CountdownActivity extends Activity {
    private TextView tvCountdownCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        tvCountdownCount = (TextView) findViewById(R.id.textViewCountdownCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.countdown, menu);
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

    public void onBegin(View v) {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountdownCount.setText("seconds remaining:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tvCountdownCount.setText("shoot");
            }
        }.start();
    }
}
