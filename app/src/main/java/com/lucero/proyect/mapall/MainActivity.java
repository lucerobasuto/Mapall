package com.lucero.proyect.mapall;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener{


    private final LatLng ITCA = new LatLng(18.371104, -100.680355);
    private GoogleMap mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(ITCA, 15));

        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMyLocationButtonEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.getUiSettings().setRotateGesturesEnabled(true);
        mapa.getUiSettings().setZoomGesturesEnabled(true);

        mapa.addMarker(new MarkerOptions().position(ITCA).title("ITCA").snippet("ITCA").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }

    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(ITCA));
    }

    public void animateCamera(View view) {
        if (mapa.getMyLocation()!= null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapa.getMyLocation().getLatitude(),mapa.getMyLocation().getLongitude()),15));
    }

    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(new LatLng(mapa.getCameraPosition().target.latitude, mapa.getCameraPosition().target.longitude)));
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.action_settings2) {
            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.action_settings3) {
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.action_settings4) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.action_settings5) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
