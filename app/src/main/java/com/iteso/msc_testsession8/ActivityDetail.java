package com.iteso.msc_testsession8;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by oscarvargas on 08/03/15.
 */
public class ActivityDetail extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Debe validarse si el dispositivo se ha cambiado para el modo landscape
        // Si s�, se finaliza la actividad y se recarga la actividad inicial
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.details_activity_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String s = extras.getString("value");
            TextView view = (TextView) findViewById(R.id.textoDetalle);
            view.setText(s);
        }
    }
}
