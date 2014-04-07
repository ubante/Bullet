package com.ubante.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class JoinActivity extends Activity {
    private EditText etName;
    private Button btnJoin;
    private TextView tvSquad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        etName = (EditText) findViewById(R.id.etName);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        tvSquad = (TextView) findViewById(R.id.tvSquad);

        // See who is logged in
        Parse.initialize(this, "49xRaGT7dNiwuCp5EEZjf9KRFoVtJ12aygBRNcEg", "zse7ogRQqfKEzNgqVNWzv9MIHKGe5a2xbAZhaK9v");
        updateSquadList();
//        ParseObject testObject = new ParseObject("TestObject");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.join, menu);
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

    public void updateSquadList() {
        // Get active members from Parse table
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ActiveMemberList");

        // If there are members, list them

        // Else give a default message
    }

    public void onJoin(View v) {
        Toast.makeText(this, "You are joining", Toast.LENGTH_SHORT).show();

        // Create a unique screenname
        String screenName = "SixtyGunner";

        // Get Android ID so we can have an immutable key
        String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        // Update Parse table
        ParseObject activeMember = new ParseObject("ActiveMemberList");
        activeMember.put("androidId",androidId);
        activeMember.put("screenName",screenName);
        activeMember.saveInBackground();

        // XXX Need to toggle the join button

        // Update squad list
        updateSquadList();
    }
}
