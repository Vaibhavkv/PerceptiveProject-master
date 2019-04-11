package com.example.perceptive;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //b.setEnabled(false);
    }

    public void reg_clk(View view) {
        String name = et1.getText().toString().trim();
        String email = et2.getText().toString().trim();
        String password = et3.getText().toString().trim();
        String phone = et4.getText().toString().trim();

        Toast.makeText(this, "something", Toast.LENGTH_SHORT).show();
    }

    public void regCancel_clk(View v) {
        this.finish();
    }
}
