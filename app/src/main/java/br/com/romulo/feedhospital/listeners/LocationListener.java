package br.com.romulo.feedhospital.listeners;

import android.location.Location;
import android.os.Bundle;

/**
 * Created by romul_000 on 28/03/2016.
 */
public class LocationListener implements android.location.LocationListener {

    private Location location;

    public Location getLocation() {
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
