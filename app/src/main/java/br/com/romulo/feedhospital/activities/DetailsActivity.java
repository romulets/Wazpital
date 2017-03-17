package br.com.romulo.feedhospital.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.adapters.contact.ContactsApadpter;
import br.com.romulo.feedhospital.listeners.ContactItemListener;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.models.HospitalState;

/**
 * Created by romul_000 on 25/03/2016.
 */
public class DetailsActivity extends Activity {
    public static final String EXTRA_HOSPITAL = "br.com.romulo.feedhospital.EXTRA_HOSPITAL";
    public static final int RESULT_CODE = 478;

    private ImageView hospitalImage;
    private TextView hospitalName;
    private TextView hospitalDistance;
    private TextView hospitalState;
    private TextView hospitalDescription;
    private TextView hospitalAddress;
    private Hospital hospital;
    private RecyclerView recyclerView;
    private FloatingActionButton rateFab;
    private FloatingActionButton recommendedFab;
    private FloatingActionButton complicatedFab;
    private FloatingActionButton badFab;

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        this.setContentView(R.layout.activity_details);
        this.initComponents();
    }

    private void initComponents(){
        if(getIntent().hasExtra(EXTRA_HOSPITAL)) {
            this.hospital = (Hospital) getIntent().getSerializableExtra(EXTRA_HOSPITAL);
        }else{
            Toast.makeText(this, R.string.details_hospital_not_found, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        this.hospitalImage = (ImageView) findViewById(R.id.hospital_image);
        this.hospitalDistance = (TextView) findViewById(R.id.hospital_distance);
        this.hospitalName = (TextView) findViewById(R.id.hospital_name);
        this.hospitalState = (TextView) findViewById(R.id.hospital_state);
        this.hospitalDescription = (TextView) findViewById(R.id.hospital_description);
        this.hospitalAddress = (TextView) findViewById(R.id.hospital_address);
        this.recyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        this.rateFab = (FloatingActionButton) findViewById(R.id.details_fab_rate);
        this.recommendedFab = (FloatingActionButton) findViewById(R.id.details_fab_recommended);
        this.complicatedFab = (FloatingActionButton) findViewById(R.id.details_fab_complicated);
        this.badFab = (FloatingActionButton) findViewById(R.id.details_fab_bad);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.hydrateView();
    }

    private void hydrateView() {
        Glide.with(this).load(hospital.getImageURL()).into(this.hospitalImage);

        if(hospital.getDistance() > -1) {
            int distanceKm =  Math.round((hospital.getDistance().floatValue() / 1000));
            hospitalDistance.setText(String.format("%d km", distanceKm));
        } else {
            hospitalDistance.setText(R.string.details_could_not_retrieve_location);
        }

        hospitalName.setText(hospital.getName());
        hospitalState.setText(hospital.getState().getStringResource());
        hospitalDescription.setText(hospital.getDescrpition());
        hospitalAddress.setText(hospital.getAddress().toString());
        recyclerView.setAdapter(new ContactsApadpter(hospital.getContacts(), this, new ContactItemListener(this)));
        recyclerView.getAdapter().notifyDataSetChanged();
        this.changeVote();
        switch (hospital.getState()) {
            case RECOMMENDED:
                hospitalState.setBackgroundColor(getResources().getColor(R.color.recommendedGreen));
                break;
            case COMPLICATED:
                hospitalState.setBackgroundColor(getResources().getColor(R.color.complicatedYellow));
                break;
            default:
                hospitalState.setBackgroundColor(getResources().getColor(R.color.badRed));
        }
    }

    private void changeVote(){
        if(this.hospital.getVotedState() != null) {
            switch (this.hospital.getVotedState()) {
                case RECOMMENDED:
                    this.rateFab.setImageResource(R.mipmap.ic_mood);
                    this.rateFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.recommendedGreen)));
                    break;
                case COMPLICATED:
                    this.rateFab.setImageResource(R.mipmap.ic_sentiment_neutral);
                    this.rateFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.complicatedYellow)));
                    break;
                case BAD:
                    this.rateFab.setImageResource(R.mipmap.ic_mood_bad);
                    this.rateFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.badRed)));
                    break;
            }
            this.rateFab.setOnClickListener(null);
        }
    }

    public void back(View view) {
        finish();
    }

    public void openMaps(View view) {
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)",
                hospital.getAddress().getLatitude(),
                hospital.getAddress().getLongitude(),
                Uri.encode("Hospital "+hospital.getName()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else{
            Toast.makeText(this, R.string.details_could_not_open_maps, Toast.LENGTH_SHORT).show();
        }
    }

    public void showRateButtons(View view){
        if(recommendedFab.getVisibility() == View.INVISIBLE){
            recommendedFab.setVisibility(View.VISIBLE);
            complicatedFab.setVisibility(View.VISIBLE);
            badFab.setVisibility(View.VISIBLE);
        } else {
            recommendedFab.setVisibility(View.INVISIBLE);
            complicatedFab.setVisibility(View.INVISIBLE);
            badFab.setVisibility(View.INVISIBLE);
        }
    }

    public void doRecommendedAction(View view) {
        this.hospital.setVotedState(HospitalState.RECOMMENDED);
        this.putExtra();
        Toast.makeText(this, "Obrigado por sua contribuição!", Toast.LENGTH_LONG).show();
        showRateButtons(view);
        this.changeVote();
    }

    public void doComplicatedAction(View view) {
        this.hospital.setVotedState(HospitalState.COMPLICATED);
        this.putExtra();
        Toast.makeText(this, "Obrigado por sua contribuição!", Toast.LENGTH_LONG).show();
        showRateButtons(view);
        this.changeVote();
    }

    public void doBadAction(View view) {
        this.hospital.setVotedState(HospitalState.BAD);
        this.putExtra();
        Toast.makeText(this, "Obrigado por sua contribuição!", Toast.LENGTH_LONG).show();
        showRateButtons(view);
        this.changeVote();
    }

    private void putExtra(){
        Intent output = new Intent();
        output.putExtra(DetailsActivity.EXTRA_HOSPITAL, this.hospital);
        setResult(RESULT_OK, output);
    }

}