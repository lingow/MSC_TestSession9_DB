package com.iteso.msc_testsession8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ActivityMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.menu_btnAutor:
                Intent authorIntent = new Intent(this, ActivityAuthor.class);
                startActivity(authorIntent);
                break;
            case R.id.menu_btnLibro:
                Intent bookIntent = new Intent(this, ActivityBook.class);
                startActivity(bookIntent);
                break;
            case R.id.menu_btnConsulta:
                Intent libraryIntent = new Intent(this, ActivityLibrary.class);
                startActivity(libraryIntent);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_logout:

                SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(this, ActivityLogin.class);
                startActivity(intent);
                finish();

                return true;
            case R.id.action_settings:
                Intent preference = new Intent(this, ActivityPreference.class);
                startActivity(preference);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
