package com.example.perceptive;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity {
    int flag=0;

    EditText etu, etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etu = findViewById(R.id.login_et_username);
        etp = findViewById(R.id.login_et_password);

        ActionBar ab = getSupportActionBar();
        ab.hide();
    }

    public void login_bclk (View v) {
        String enteredUsername = etu.getText().toString().trim().toLowerCase();
        String enteredPassword = etp.getText().toString().trim().toLowerCase();

        TinyDB tinydb = new TinyDB(this);
        ArrayList<Object> userdata = tinydb.getListObject("userdata", user.class);

        for(Object objs : userdata){
            user temp = (user)objs;
            if((temp.email.equals(enteredUsername))&&(temp.password.equals(enteredPassword))){
                flag=1;
                Intent i = new Intent(this, homePageActivity.class);
                startActivity(i);
            }
        }
        if(flag==0){
            etu.setText("");
            etp.setText("");
            etp.setError("Invalid Credentials");
            etu.setError("Invalid Credentials");
        }
    }

    public void cancel_bclk(View v) {
        this.finish();
    }
}
