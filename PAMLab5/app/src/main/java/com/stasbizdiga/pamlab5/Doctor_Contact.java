package com.stasbizdiga.pamlab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;


public class Doctor_Contact extends Activity{// implements OnMapReadyCallback {

    //private GoogleMap googleMap;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(Doctor_Contact.this, Home.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(Doctor_Contact.this, Approve.class);
                    startActivity(intent2);
                    break;
                case R.id.plus:
                    Intent intent3 = new Intent(Doctor_Contact.this, Home.class);
                    startActivity(intent3);
                    break;
                case R.id.schedule:
                    Intent intent4 = new Intent(Doctor_Contact.this, Doctor_List.class);
                    startActivity(intent4);
                    break;
                case R.id.profile:
                    Intent intent5 = new Intent(Doctor_Contact.this, Doctor_Contact.class);
                    startActivity(intent5);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_contact);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.plus);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //MapFragment mapFragment = (MapFragment) getFragmentManager()
        //        .findFragmentById(R.id.mapView);
        //mapFragment.getMapAsync(this);
    }

    public void btnRequest(View view)
    {
        Intent intent = new Intent(Doctor_Contact.this, Approve.class);
        startActivity(intent);
    }


/*
    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(false);
        map.setIndoorEnabled(false);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        googleMap = map;
    }
*/

}
