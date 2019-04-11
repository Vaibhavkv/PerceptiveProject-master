package com.example.perceptive;

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
}
