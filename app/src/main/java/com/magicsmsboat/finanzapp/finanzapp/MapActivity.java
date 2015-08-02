package com.magicsmsboat.finanzapp.finanzapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtData;

public class MapActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private FinanzamtData mDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras)
        {
            mDetailData = (FinanzamtData) extras.getSerializable(DetailActivity.DETAIL_DATA);
        }

        setContentView(R.layout.activity_show_map);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap()
    {
        if (null == mDetailData) return;

        double latitude = Double.parseDouble(mDetailData.getDataItem(FinanzamtData.DisLatitude));
        double longitude = Double.parseDouble(mDetailData.getDataItem(FinanzamtData.DisLongitude));
        LatLng pos = new LatLng(latitude, longitude);

        MarkerOptions marker = new MarkerOptions();
        marker.position(pos);
        marker.title(mDetailData.getDataItem(FinanzamtData.DisStrasse));
        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.zoomTo(mMap.getMaxZoomLevel()*0.8f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }
}
