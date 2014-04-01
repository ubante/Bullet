package com.ubante.bullet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

    public void onDiagonal(View v) {
        Toast.makeText(this,"Switching to Diagonal",Toast.LENGTH_SHORT).show();
        Intent diagonalIntent = new Intent(this, DiagonalActivity.class);
        startActivity(diagonalIntent);
    }

    public void onShape(View v) {
        Toast.makeText(this,"Switching to Shapes",Toast.LENGTH_SHORT).show();
        Intent shapeIntent = new Intent(this, ShapeActivity.class);
        startActivity(shapeIntent);
    }

    public void onDrawing(View v) {
        Toast.makeText(this,"Switching to Drawing",Toast.LENGTH_SHORT).show();
        Intent drawingIntent = new Intent(this, DrawingActivity.class);
        startActivity(drawingIntent);
    }

    public void onCountdown(View v) {
        Toast.makeText(this,"Switching to Countdown",Toast.LENGTH_SHORT).show();
        Intent countdownIntent = new Intent(this, CountdownActivity.class);
        startActivity(countdownIntent);
    }

    public void onScratch(View v) {
        Toast.makeText(this,"Switching to Scratch",Toast.LENGTH_SHORT).show();
        Intent scratchIntent = new Intent(this, ScratchActivity.class);
        startActivity(scratchIntent);
    }

    public void onUnimplemented(View v) {
        Toast.makeText(this,"This button is not yet implemented",Toast.LENGTH_SHORT).show();
    }


}
