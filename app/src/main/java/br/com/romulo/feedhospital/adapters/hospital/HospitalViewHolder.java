package br.com.romulo.feedhospital.adapters.hospital;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.romulo.feedhospital.R;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.listeners.OnItemClickListener;

/**
 * Created by romul_000 on 19/03/2016.
 */
public class HospitalViewHolder extends RecyclerView.ViewHolder {

    private TextView hospitalName;
    private TextView hospitalState;
    private ImageView hospitalImage;
    private RelativeLayout hospitalContent;
    private RelativeLayout hospitalSateCircle;


    public HospitalViewHolder(View view) {
        super(view);
        this.hospitalName = (TextView) view.findViewById(R.id.name);
        this.hospitalState = (TextView) view.findViewById(R.id.state);
        this.hospitalImage = (ImageView) view.findViewById(R.id.hospital_image);
        this.hospitalContent = (RelativeLayout) view.findViewById(R.id.hospital_content);
        this.hospitalSateCircle = (RelativeLayout) view.findViewById(R.id.hospital_state_circle);
    }

    public void bind(final Hospital hospital, final OnItemClickListener listener) {
        hospitalContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(hospital);
            }
        });
    }

    public TextView getHospitalName() {
        return hospitalName;
    }

    public TextView getHospitalState() {
        return hospitalState;
    }

    public ImageView getHospitalImage() {
        return hospitalImage;
    }

    public RelativeLayout getHospitalSateCircle() {
        return hospitalSateCircle;
    }
}
