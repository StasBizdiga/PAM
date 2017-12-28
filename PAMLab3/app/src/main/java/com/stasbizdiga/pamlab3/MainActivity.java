package com.stasbizdiga.pamlab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.stasbizdiga.pamlab3.adapters.PostsAdapter;
import com.stasbizdiga.pamlab3.models.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // OUR RSS ADDRESS
    private String RSS_URL;

    // RSS TO XML
    private PullAndParseXML pullAndParseXML;

    private List<Post> posts;
    private RecyclerView recyclerView;
    private PostsAdapter adapter;

    Spinner spinner ; // UI element to choose from list of RSS
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.feed_names, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                RSS_URL = spinner.getSelectedItem().toString();

                pullAndParseXML = new PullAndParseXML(RSS_URL);
                pullAndParseXML.downloadXML();

                while (pullAndParseXML.parsingComplete) ;
                posts = pullAndParseXML.getPostList().subList(1, pullAndParseXML.getPostList().size());

                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                adapter = new PostsAdapter(MainActivity.this, posts);

                RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}