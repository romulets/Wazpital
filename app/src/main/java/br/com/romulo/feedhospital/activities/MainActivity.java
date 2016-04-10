package br.com.romulo.feedhospital.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.adapters.hospital.HospitalsAdapter;
import br.com.romulo.feedhospital.listeners.HospitalItemListener;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.util.JsonHospitalLoader;

/**
 * Created by romul_000 on 19/03/2016.
 */
public class MainActivity extends Activity implements android.location.LocationListener {

    /**
     * Procurar OnActivityResult
     */
    private ArrayList<Hospital> hospitals;
    private RecyclerView.Adapter adapter;
    private Location location;

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        this.setContentView(R.layout.activity_main);
        this.initComponents();

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DetailsActivity.RESULT_CODE && resultCode == RESULT_OK && data != null) {
            Hospital hospital = (Hospital) data.getSerializableExtra(DetailsActivity.EXTRA_HOSPITAL);
            for(Hospital listedHospital : hospitals) {
                if(listedHospital.getId() == hospital.getId()) {
                    listedHospital.setVotedState(hospital.getVotedState());
                }
            }
        }
    }

    public void initComponents() {
        this.hospitals = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.hospitals_list);
        this.adapter = new HospitalsAdapter(hospitals, this, new HospitalItemListener(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.adapter);

        if(!isOnline()){
            Toast.makeText(this, R.string.main_no_connection, Toast.LENGTH_LONG).show();
        }

        JsonHospitalLoader jsonHP= new JsonHospitalLoader(this, location);
        jsonHP.load(hospitals);
        this.adapter.notifyDataSetChanged();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    public Location getLocation() {
        return location;
    }

}
