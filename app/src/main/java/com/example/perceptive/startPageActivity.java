package com.example.perceptive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;


public class startPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        ArrayList<user> userdatatemp = new ArrayList<user>();
        ArrayList<Object> userdata = new ArrayList<Object>();
        /*userdatatemp.add(new user("1","Tom","tom@gmail.com","tom","1111111111"));
        userdatatemp.add(new user("2","Jack","jack@gmail.com","jack","2222222222"));

        for(user a : userdatatemp){
            userdata.add((Object)a);
        }

        ArrayList<item> preferencesdatatemp = new ArrayList<item>();
        ArrayList<Object> preferencesdata = new ArrayList<Object>();
        //preferencesdata.add(new item("");

        for(item a : preferencesdatatemp){
            preferencesdata.add((Object)a);
        }

        TinyDB tinydb = new TinyDB(this);
        tinydb.putListObject("preferencesdata", preferencesdata);
        tinydb.putListObject("userdata", userdata);*/

        /*TinyDB tinydb = new TinyDB(this);
        ArrayList<Object> preferencesdata = tinydb.getListObject("preferencesdata", item.class);
        for(Object objs : preferencesdata) {
            item temp = (item) objs;
            Toast.makeText(this,temp.userid+" "+temp.name,Toast.LENGTH_LONG).show();
        }*/

    }

    public void login_clk(View v) {
        Intent i = new Intent(startPageActivity.this, loginActivity.class);
        startActivity(i);
    }

    public void reg_clk(View v) {
        Intent i = new Intent(startPageActivity.this, RegActivity.class);
        startActivity(i);
    }


    private static final int PRESS_INTERVAL = 1000;
    private long mUpKeyEventTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_VOLUME_DOWN == event.getKeyCode()) {
            if ((event.getEventTime() - mUpKeyEventTime) < PRESS_INTERVAL) {
                AlertDialog alertDialog = new AlertDialog.Builder(startPageActivity.this).create();
                alertDialog.setTitle("Made By");
                alertDialog.setMessage("Rajit Roy\nRaghav Srivastava\nVaibhav KV");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_VOLUME_UP == keyCode){
            mUpKeyEventTime = event.getEventTime();
        }
        return super.onKeyUp(keyCode, event);
    }

}
