package com.example.perceptive;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Vector;


public class startPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        checkPermission();

        ArrayList<user> userdatatemp = new ArrayList<user>();
        ArrayList<Object> userdata = new ArrayList<Object>();
        userdatatemp.add(new user(1,"Tom","tom@gmail.com","tom","1111111111"));
        userdatatemp.add(new user(2,"Jack","jack@gmail.com","jack","2222222222"));

        for(user a : userdatatemp){
            userdata.add((Object)a);
        }

        TinyDB tinydb = new TinyDB(this);
        tinydb.putListObject("userdata", userdata);
    }

    public void login_clk(View v) {
        Intent i = new Intent(startPageActivity.this, loginActivity.class);
        startActivity(i);
    }

    public void reg_clk(View v) {
        Intent i = new Intent(startPageActivity.this, RegActivity.class);
        startActivity(i);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
}
