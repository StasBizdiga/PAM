package com.stasbizdiga.pamlab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;



public class Approve extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(Approve.this, Home.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(Approve.this, Approve.class);
                    startActivity(intent2);
                    break;
                case R.id.plus:
                    Intent intent3 = new Intent(Approve.this, Home.class);
                    startActivity(intent3);
                    break;
                case R.id.schedule:
                    Intent intent4 = new Intent(Approve.this, Doctor_List.class);
                    startActivity(intent4);
                    break;
                case R.id.profile:
                    Intent intent5 = new Intent(Approve.this, Doctor_Contact.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.plus);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void btnConfirm(View view)
    {
        Intent intent = new Intent(Approve.this, Doctor_Contact.class);
        startActivity(intent);
    }

    public void btnCancel(View view)
    {
        Intent intent = new Intent(Approve.this, Doctor_Contact.class);
        startActivity(intent);
    }
}
