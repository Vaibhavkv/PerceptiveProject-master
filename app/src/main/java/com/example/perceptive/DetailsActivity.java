package com.example.perceptive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.perceptive.fetch_details_artists.a1;

public class DetailsActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvn, tvd, tvx;
    Button addb, shrb;

    String name = "", desc = "", ext = "", imgurl = "";
    String mode = "", type = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        iv = findViewById(R.id.imageStuff);
        tvn = findViewById(R.id.tv_det_name);
        tvx = findViewById(R.id.tv_extra);
        tvd = findViewById(R.id.tv_det_det);
        addb = findViewById(R.id.det_btn_add);
        shrb = findViewById(R.id.det_btn_shr);

        name = getIntent().getExtras().getString("Name");
        desc = getIntent().getExtras().getString("Desc");
        ext = getIntent().getExtras().getString("Extra");
        imgurl = getIntent().getExtras().getString("Img");
        mode = getIntent().getExtras().getString("Mode");
        type = getIntent().getExtras().getString("Type");

        tvn.setText(name);
        tvx.setText(ext);
        tvd.setText(desc);

        Picasso.get().load(imgurl).into(iv);

        if (mode.equals("0")) {
            addb.setVisibility(View.GONE);
        }
    }

    public void add_clk(View v) {
        PopupMenu pm = new PopupMenu(this, addb);
        pm.getMenuInflater().inflate(R.menu.wanna_popupmenu, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.played:
                        return true;
                    case R.id.wanna_play:
                        return true;
                    default:
                        return false;
                }
            }
        });
        pm.show();
    }

    public void shr_clk (View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = name;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, name);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
