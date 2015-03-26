package com.iteso.msc_testsession8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends ActionBarActivity {

    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User user = loadPreferences();
        if(user.isLogged()){
            openMainActivity();
        }

        username = (EditText) findViewById(R.id.activity_login_user);
        password = (EditText) findViewById(R.id.activity_login_password);
        login = (Button) findViewById(R.id.activity_login_send);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
    }

    public void savePreferences(){

        User user = new User();
        user.setName(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setLogged(true);

        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER", user.getName());
        editor.putString("PWD", user.getPassword());
        editor.putBoolean("LOGGED", user.isLogged());
        editor.commit();

    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("USER", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }

}
