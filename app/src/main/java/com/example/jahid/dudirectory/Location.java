package com.example.jahid.dudirectory;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String department, building;
    float lattitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        department = getIntent().getExtras().getString("department");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        Cursor location = databaseOperation.getLocation(databaseOperation,department);
        location.moveToFirst();
        if(location.getCount()==0){
            Toast.makeText(this, "Not Available", Toast.LENGTH_SHORT).show();
        }else {
            building = location.getString(0);
            lattitude = location.getFloat(1);
            longitude = location.getFloat(2);

            // Add a marker in Sydney and move the camera
            LatLng position = new LatLng(lattitude, longitude);
            mMap.addMarker(new MarkerOptions().position(position).title(department))
                    .setSnippet(building);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
        }

    }
}
