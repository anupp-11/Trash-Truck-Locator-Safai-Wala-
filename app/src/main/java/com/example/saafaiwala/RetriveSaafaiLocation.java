package com.example.saafaiwala;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class RetriveSaafaiLocation extends FragmentActivity implements OnMapReadyCallback {

    int count = 0;
    private GoogleMap mMap;

    private long Update_Interval = 2000;
    private long Fastest_Interval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_track_location);

      /* String url = getUrl("location1", "location2", "driving");
       new FetchURL (RetriveSaafaiLocation.this).execute(url,"driving");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
        content();
    }

    public  void content(){

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        refresh(6000);
    }

    private void refresh(int millisecond) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();;
            }
        };
        handler.postDelayed(runnable, millisecond);
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
    public void onMapReady(GoogleMap googleMap)
     {
        mMap = googleMap;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Saafai Current Location");
        ValueEventListener listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Double latitude1 = dataSnapshot.child("latitude").getValue(Double.class);
                Double longitude1 = dataSnapshot.child("longitude").getValue(Double.class);

                LatLng location1 = new LatLng(latitude1,longitude1);


                mMap.addMarker(new MarkerOptions().position(location1).title("Marker location").icon(BitmapDescriptorFactory.fromResource(R.drawable.trashmarker)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1,16f));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


         DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Customer Current Location");
         ValueEventListener listener2 = databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 Double latitude2 = dataSnapshot.child("latitude").getValue(Double.class);
                 Double longitude2 = dataSnapshot.child("longitude").getValue(Double.class);

                 LatLng location2 = new LatLng(latitude2,longitude2);


                 mMap.addMarker(new MarkerOptions().position(location2).title("Marker location1").icon(BitmapDescriptorFactory.fromResource(R.drawable.usermarker)));
                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location2,16f));


             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });



    }

    /*    private String getUrl(String origin, String dest, String directionMode){
        String str_origin = "origin" + "lalitude" + ","+ "longitude";

        String str_dest = "destination" + "latitude" + ","+ "longitude";

        String mode = "mode = "+ directionMode;

        String parameters = str_origin + "&" + str_dest +"&" + mode;

        String output = "json";

        String url = "https://maps.google.com/maps/api/direction/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }*/

}

