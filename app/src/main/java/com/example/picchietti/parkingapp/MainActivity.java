package com.example.picchietti.parkingapp;

import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.choices);
        String[] colors = {"Red","Green","Blue","Yellow","Gray"};
        for(int i=0, y=colors.length;i<y;i++){
            View view = relLayout.getChildAt(i);
            view.setTag(colors[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Store in settings. Could be passed through intent, but this will persist.
                    SharedPreferences settings = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("sticker", (String) v.getTag());
                    editor.commit();

                    // Here is an example of accessing the sticker color.
                    Log.d("sticker", settings.getString("sticker", ""));

                    // Go to the next Activity.
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
