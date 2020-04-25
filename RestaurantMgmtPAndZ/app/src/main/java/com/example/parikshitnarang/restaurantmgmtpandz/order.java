package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class order extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lv=(ListView)findViewById(R.id.lv1);
        ArrayList<String> list=new ArrayList<>();

        list.add("Sizzler Ranch");
        list.add("Barbeque Nation");
        list.add("McDonalds");
        list.add("KFC");
        list.add("Ginger Garlic");
        list.add("Barista");
        list.add("Egg Factory");
        list.add("Froth On Top");
        list.add("Keventers");

        ArrayAdapter<String>adapter;
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String)
                        parent.getItemAtPosition(position);
                TextView tv=(TextView)findViewById(R.id.tv_show);
                tv.setText(selected);
                if(selected.equals("KFC")){
                    Intent intent = new Intent(getApplicationContext(),KFC.class);
                    startActivity(intent);
                }
            }
        }); }

}




