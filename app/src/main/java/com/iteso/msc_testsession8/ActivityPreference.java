package com.iteso.msc_testsession8;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by oscarvargas on 02/03/15.
 */
public class ActivityPreference extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new FragmentPreference())
                .commit();
    }


}
