 package com.ubante.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

 public class DiagonalActivity extends Activity {
    private DiagonalDrawingView diagonal;
    Point start,end;
    private TextView tvScoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagonal);
        tvScoreString = (TextView) findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diagonal, menu);
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

    public void onScoreMe(View v) {
        String score;

        diagonal = (DiagonalDrawingView) findViewById(R.id.diagonalDrawingView1);
        if (diagonal.getPath().isEmpty()) {
            score = "You should try drawing a line";
        } else {
            start = diagonal.getStartPoint();
            end = diagonal.getEndPoint();
            float slope = diagonal.getSlope();
            score = String.format("Start: %s  End: %s  Slope: %-5.2f\n",
                    start.toString(),end.toString(),slope);
            if ((slope == Float.POSITIVE_INFINITY) || (slope == Float.NEGATIVE_INFINITY)) {
                score = score+"Congratulations - that was infinity";
            } else if (Math.abs(slope) > 300) {
                score = score+"Getting real close";
            } else if (Math.abs(slope) > 100) {
                score = score+"Getting close";
            } else if (Math.abs(slope) < 1) {
                score = score+"That's a horizontal line";
            } else {
                score = score+"Keep trying";
            }
        }

        tvScoreString.setText(score);
    }

}
