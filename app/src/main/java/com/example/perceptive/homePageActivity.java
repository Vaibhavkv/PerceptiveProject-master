package com.example.perceptive;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class homePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();
    }

    public void search_bclk (View v) {
        Intent i = new Intent(this, searchActivity.class);
        startActivity(i);
    }

    public void art_clk(View v) {
        Intent i = new Intent(this, ListActivity.class);
        i.putExtra("pgt", "1");
        startActivity(i);
    }
    public void mov_clk(View v){
        Intent i = new Intent(this, ListActivity.class);
        i.putExtra("pgt", "0");
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Logout?");
        builder.setMessage("Do you want to logout? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                homePageActivity.this.finish();
                //homePageActivity.this.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
