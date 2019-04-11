package com.example.perceptive;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        et1 = findViewById(R.id.et_reg_name_et);
        et2 = findViewById(R.id.et_reg_em_et);
        et3 = findViewById(R.id.et_reg_pswd_et);
        et4 = findViewById(R.id.et_Reg_phno_et);

        b = findViewById(R.id.reg_button_reg);
        b.setEnabled(false);
    }

    public void reg_clk(View view) {
        String name = et1.getText().toString().trim().toLowerCase();
        String email = et2.getText().toString().trim().toLowerCase();
        String password = et3.getText().toString().trim();
        String phone = et4.getText().toString().trim();

        if (!name.matches("[a-zA-Z]+")) {
            et1.setText("");
            et1.setError("Should contain only alphabets");
        }
        if (phone.length() != 10) {
            et4.setText("");
            et4.setError("Invalid phone number");
        }
        if(name.length()==0){
            et1.setError("Cannot be blank");
        }
        if(email.length()==0){
            et2.setError("Cannot be blank");
        }
        if(password.length()==0){
            et3.setError("Cannot be blank");
        }
        if(phone.length()==0){
            et4.setError("Cannot be blank");
        }
        if ((name.matches("[a-zA-Z]+")) && (phone.length() == 10) && (name.length()!=0) && (email.length()!=0) && (password.length()!=0)){
            user a = new user(String.valueOf(user.count++), name, email, password, phone);
            TinyDB tinydb = new TinyDB(this);
            ArrayList<Object> userdata = tinydb.getListObject("userdata", user.class);
            userdata.add((Object) a);
            tinydb.putListObject("userdata", userdata);

            Intent i = new Intent(this, loginActivity.class);
            startActivity(i);
        }

    }

    public void regCancel_clk(View v) {
        this.finish();
    }

    public void tnc_clk(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cb_tnc:
                if (checked) {
                    b.setEnabled(true);
                }
                else {
                    b.setEnabled(false);
                }
                break;
        }
    }
}
