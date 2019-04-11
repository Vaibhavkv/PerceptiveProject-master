package com.example.perceptive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.perceptive.loginActivity.userid;

public class ListActivity extends AppCompatActivity {
    String type;
    ListView lvw, lvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        type = getIntent().getExtras().getString("pgt");
        lvw = findViewById(R.id.lv_wanna);
        lvp = findViewById(R.id.lv_played);

        ArrayList<String> al1 = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();

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

        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al1);
        lvw.setAdapter(aa1);
        ArrayAdapter<String> aa2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al2);
        lvp.setAdapter(aa2);
        setListViewHeightBasedOnChildren(lvw);
        setListViewHeightBasedOnChildren(lvp);
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
