package com.ubante.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.ubante.bullet.model.Shooter;
import com.ubante.bullet.model.ShooterList;

import java.util.List;

public class JoinActivity extends Activity {
    private EditText etName;
    private Button btnJoin;
    private TextView tvSquad,tvDisplayName;
    ShooterList shooterList = new ShooterList();
    Shooter iamShooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        etName = (EditText) findViewById(R.id.etName);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        tvSquad = (TextView) findViewById(R.id.tvSquad);
        tvDisplayName = (TextView) findViewById(R.id.tvScreenNameSentence);

        // XXX Need to dig up iamShooter from something stored on the device's storage
        // in the meantime
        iamShooter = new Shooter("BravoLeader");
        changeNameOnScreen(iamShooter.getHumanName());

        // See who is logged in
        Parse.initialize(this, "49xRaGT7dNiwuCp5EEZjf9KRFoVtJ12aygBRNcEg", "zse7ogRQqfKEzNgqVNWzv9MIHKGe5a2xbAZhaK9v");
        updateSquadList();
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
        final String emptyMessage = "There are no active members in the squad.";

        // Get active members from Parse table
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ActiveMemberList");
        query.whereExists("screenName");
        query.setLimit(100); // 100 is the Parse default
        query.orderByAscending("screenName");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> memberList, ParseException e) {
                // Log errors
                if (e != null) {
                    Log.d("screenName", "Error: " + e.getMessage());
                    return;
                }

                // Log and toast success
                Log.d("screenName", "Retrieved " + memberList.size() + " scores");
                Toast.makeText(JoinActivity.this,"found "+memberList.size()+ " shooters",Toast.LENGTH_SHORT).show();

                // Populate shooterList from the active users found in Parse
                // XXX ShooterList class should do this
                shooterList.clear();
                for (ParseObject member : memberList) {
                    Shooter shooter = new Shooter(member.getString("screenName"));
                    shooter.joinShooterList(shooterList);
                }

                // Update the list on the screen
                if (shooterList.size() > 0) {
                    tvSquad.setText(shooterList.getShooterStringList());
                } else {
                    tvSquad.setText(emptyMessage);
                }

            }
        });
    }

    public void onJoin(View v) {
        Toast.makeText(this, "You are joining", Toast.LENGTH_SHORT).show();

        // Get Android ID so we can have an immutable key
        String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        // Update Parse table
        ParseObject activeMember = new ParseObject("ActiveMemberList");
        activeMember.put("androidId",androidId);
        activeMember.put("screenName",iamShooter.getHumanName());
        activeMember.saveInBackground();

        // XXX Need to toggle the join button

        // Update squad list
        updateSquadList();
    }

    void changeNameOnScreen(String newName) {
        tvDisplayName.setText("Your screenname is \""+newName+"\"");
    }

    public void onChangeName(View v) {
        // XXX this should be replaced with a TextWatcher on the EditText....
        String newName = etName.getText().toString();
        changeNameOnScreen(newName);
        iamShooter.setHumanName(newName);
    }
}
