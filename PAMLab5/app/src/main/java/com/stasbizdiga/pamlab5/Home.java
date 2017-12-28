package com.stasbizdiga.pamlab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(Home.this, Home.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(Home.this, Approve.class);
                    startActivity(intent2);
                    break;
                case R.id.plus:
                    break;
                case R.id.schedule:
                    Intent intent4 = new Intent(Home.this, Doctor_List.class);
                    startActivity(intent4);
                    break;
                case R.id.profile:
                    Intent intent5 = new Intent(Home.this, Doctor_Contact.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.plus);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void btnRequest(View view)
    {
        Intent intent = new Intent(Home.this, Approve.class);
        startActivity(intent);
    }


}