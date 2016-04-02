package br.com.romulo.feedhospital.adapters.hospital;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.models.HospitalState;
import br.com.romulo.feedhospital.listeners.OnItemClickListener;

/**
 * Created by romul_000 on 19/03/2016.
 */
public class HospitalsAdapter extends RecyclerView.Adapter{

    private ArrayList<Hospital> hospitals;
    private Context context;
    private OnItemClickListener<Hospital> listener;

    public HospitalsAdapter(ArrayList<Hospital> hospitals, Context context, OnItemClickListener<Hospital> listener) {
        this.hospitals = hospitals;
        this.context = context;
        this.listener = listener;
    }

    public void setHospitals(ArrayList<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.hospital_row, parent, false
        );

        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HospitalViewHolder hospitalViewHolder = (HospitalViewHolder) holder;
        Hospital hospital = this.hospitals.get(position);
        hospitalViewHolder.bind(hospital, listener);
        hospitalViewHolder.getHospitalName().setText(hospital.getName());
        hospitalViewHolder.getHospitalState().setText(hospital.getState().getStringResource());
        updateStateColor(hospital.getState(), hospitalViewHolder.getHospitalState(), hospitalViewHolder.getHospitalSateCircle());
        Picasso.with(context)
                .load(hospital.getImageURL())
                .into(hospitalViewHolder.getHospitalImage());
    }

    @Override
    public int getItemCount() {
        return this.hospitals.size();
    }

    private void updateStateColor(HospitalState state, TextView textState, RelativeLayout circleState) {
        switch (state){
            case RECOMMENDED:
                textState.setTextColor(context.getResources().getColor(R.color.recommendedGreen));
                circleState.setBackground(context.getResources().getDrawable(R.drawable.state_background_recommended));
                break;
            case COMPLICATED:
                textState.setTextColor(context.getResources().getColor(R.color.complicatedYellow));
                circleState.setBackground(context.getResources().getDrawable(R.drawable.state_background_complicated));
                break;
            case BAD:
                textState.setTextColor(context.getResources().getColor(R.color.badRed));
                circleState.setBackground(context.getResources().getDrawable(R.drawable.state_background_bad));
                break;
        }
    }
}