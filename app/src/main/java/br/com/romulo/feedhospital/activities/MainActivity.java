package br.com.romulo.feedhospital.activities;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.security.Key;
import java.util.ArrayList;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.adapters.hospital.HospitalsAdapter;
import br.com.romulo.feedhospital.listeners.HospitalItemListener;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.util.JsonHospitalLoader;

/**
 * Created by romul_000 on 19/03/2016.
 */
public class MainActivity extends Activity {

    private ArrayList<Hospital> hospitals;
    private RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        this.setContentView(R.layout.activity_main);
        this.initComponents();
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

        JsonHospitalLoader.load(this, hospitals);
        this.adapter.notifyDataSetChanged();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
