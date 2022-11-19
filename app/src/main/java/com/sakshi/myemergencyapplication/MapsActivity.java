package com.sakshi.myemergencyapplication;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sakshi.myemergencyapplication.databinding.ActivityMaps4Binding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // Add a marker in Sydney and move the camera


        LatLng user = new LatLng(12.9710, 79.1613);
        mMap.addMarker(new MarkerOptions().position(user).title("Accident Occurred Here!!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user));

        LatLng nHospital = new LatLng(12.9244, 79.1353);
        mMap.addMarker(new MarkerOptions().position(nHospital).title("Nearest Hospital (CMC)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nHospital));

        LatLng aHospital = new LatLng(13.0101, 80.2588);
        mMap.addMarker(new MarkerOptions().position(aHospital).title("A Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aHospital));

        List<LatLng> path = new ArrayList();

        path.add(user);

        LatLng r1 = new LatLng(12.9673, 79.1589);
        path.add(r1);

        LatLng r2 = new LatLng(12.9682, 79.1503);
        path.add(r2);

        LatLng r3 = new LatLng(12.9686, 79.1430);
        path.add(r3);

        LatLng r4 = new LatLng(12.9665, 79.1385);
        path.add(r4);

        LatLng r5 = new LatLng(12.9653, 79.1375);
        path.add(r5);

        LatLng r6 = new LatLng(12.9626, 79.1373);
        path.add(r6);

        LatLng r7 = new LatLng(12.9560, 79.1369);
        path.add(r7);

        LatLng r8 = new LatLng(12.9456, 79.1370);
        path.add(r8);

        LatLng r9 = new LatLng(12.9433, 79.1375);
        path.add(r9);

        LatLng r10 = new LatLng(12.9399, 79.1382);
        path.add(r10);

        LatLng r11 = new LatLng(12.9348, 79.1386);
        path.add(r11);

        LatLng r12 = new LatLng(12.9332, 79.1385);
        path.add(r12);

        LatLng r13 = new LatLng(12.9321, 79.1378);
        path.add(r13);

        LatLng r14 = new LatLng(12.9297, 79.1340);
        path.add(r14);

        LatLng r15 = new LatLng(12.9265, 79.1331);
        path.add(r15);

        LatLng r16 = new LatLng(12.9237, 79.1332);
        path.add(r16);

        path.add(nHospital);

        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }

    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}