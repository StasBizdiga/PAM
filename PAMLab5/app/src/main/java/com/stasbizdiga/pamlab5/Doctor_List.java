package com.stasbizdiga.pamlab5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import com.stasbizdiga.pamlab5.adapters.DoctorAdapter;




public class Doctor_List extends Activity {

    GridView grid;
    String[] web = {
            "Emilia Suruceanu",
            "Vitalie Enescu",
            "Alan Doga",
    } ;
    String[] point = {
            "5.0",
            "4.8",
            "4.3",
    } ;

    String[] special = {
            "Oculist",
            "Neurologist",
            "Endocrinologist",
    } ;
    String[] address = {
            "str. Popescu 4/2",
            "str. Eminescu 15",
            "str. Mihai Viteazu 25",
    } ;
    int[] imageId = {
            R.drawable.doc1,
            R.drawable.doc2,
            R.drawable.doc3,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        DoctorAdapter adapter = new DoctorAdapter(Doctor_List.this, web,point,special,address, imageId);
        grid = (GridView) findViewById(R.id.doctorlist);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Doctor_List.this, "You Selected  " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });

    }}

