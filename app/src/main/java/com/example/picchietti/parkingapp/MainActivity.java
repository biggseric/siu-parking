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
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Spinner spinner;

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

        addSpinnerItems();
        addListenerOnSpinnerItemSelection();

    }

    private void addSpinnerItems(){
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Student Center");
        list.add("Faner");
        list.add("Morris Library");
        list.add("Agriculture");
        list.add("Pulliam");
        list.add("Engineering");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);
//        "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem())
//        "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem())

    }
}
