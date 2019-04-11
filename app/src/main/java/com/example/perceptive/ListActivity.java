package com.example.perceptive;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.perceptive.fetch_details_artists.a1;
import static com.example.perceptive.fetch_similar_artists.arr;
import static com.example.perceptive.loginActivity.userid;

public class ListActivity extends AppCompatActivity {
    String type;
    ListView lvw, lvp;
    ArrayAdapter<String> aa1;
    ArrayAdapter<String> aa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        type = getIntent().getExtras().getString("pgt");
        lvw = findViewById(R.id.lv_wanna);
        lvp = findViewById(R.id.lv_played);

        getSupportActionBar().hide();

        final ArrayList<String> al1 = new ArrayList<String>();
        final ArrayList<String> al2 = new ArrayList<String>();

        TinyDB tinydb = new TinyDB(this);
        ArrayList<Object> preferencesdata = tinydb.getListObject("preferencesdata", item.class);
        for(Object objs : preferencesdata) {
            item temp = (item) objs;
            if((temp.userid.equals(userid))&&(temp.type.equals(type))&&temp.option.equals("wanna play")){
                al1.add(temp.name);
            }
            if((temp.userid.equals(userid))&&(temp.type.equals(type))&&temp.option.equals("played")){
                al2.add(temp.name);
            }

        }

        aa1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al1);
        lvw.setAdapter(aa1);
        aa2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al2);
        lvp.setAdapter(aa2);
        setListViewHeightBasedOnChildren(lvw);
        setListViewHeightBasedOnChildren(lvp);

        lvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetch_details_artists fda = null;
                fda = new fetch_details_artists(al1.get(position));
                fda.execute();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(ListActivity.this, DetailsActivity.class);
                i.putExtra("Name", a1.name);
                i.putExtra("Desc", a1.summary);
                i.putExtra("Extra", a1.listeners);
                i.putExtra("Img", a1.image_url);
                i.putExtra("Mode", "0");

                startActivity(i);
            }
        });

        lvp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetch_details_artists fda = null;
                fda = new fetch_details_artists(al2.get(position));
                fda.execute();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(ListActivity.this, DetailsActivity.class);
                i.putExtra("Name", a1.name);
                i.putExtra("Desc", a1.summary);
                i.putExtra("Extra", a1.listeners);
                i.putExtra("Img", a1.image_url);
                i.putExtra("Mode", "0");

                startActivity(i);
            }
        });

        lvw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = al1.get(position);
                al1.remove(position);
                aa1.notifyDataSetChanged();
                TinyDB tinydb = new TinyDB(ListActivity.this);
                ArrayList<Object>  preferencesdatatemp = tinydb.getListObject("preferencesdata", item.class);
                ArrayList<item> preferencesdata = new ArrayList<item>();
                int pos=0;
                for(Object objs : preferencesdatatemp){
                    item temp1 = (item) objs;
                    if((temp1.userid.equals(userid))&&(temp1.type.equals(type))&&(temp1.option.equals("wanna play"))&&(temp1.name.equals(temp))){
                        break;
                    }
                    pos++;
                }
                preferencesdatatemp.remove(pos);
                tinydb.putListObject("preferencesdata",preferencesdatatemp);
                return true;
            }
        });

        lvp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = al2.get(position);
                al2.remove(position);
                aa2.notifyDataSetChanged();
                TinyDB tinydb = new TinyDB(ListActivity.this);
                ArrayList<Object>  preferencesdatatemp = tinydb.getListObject("preferencesdata", item.class);
                ArrayList<item> preferencesdata = new ArrayList<item>();
                int pos=0;
                for(Object objs : preferencesdatatemp){
                    item temp1 = (item) objs;
                    if((temp1.userid.equals(userid))&&(temp1.type.equals(type))&&(temp1.option.equals("played"))&&(temp1.name.equals(temp))){
                        break;
                    }
                    pos++;
                }
                preferencesdatatemp.remove(pos);
                tinydb.putListObject("preferencesdata",preferencesdatatemp);
                return true;
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight=0;
        View view = null;

        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            view = listAdapter.getView(i, view, listView);

            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
                        ViewGroup.LayoutParams.MATCH_PARENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + ((listView.getDividerHeight()) * (listAdapter.getCount()));

        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}
