package br.com.romulo.feedhospital.listeners;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.romulo.feedhospital.activities.DetailsActivity;
import br.com.romulo.feedhospital.models.Hospital;

/**
 * Created by romul_000 on 25/03/2016.
 */
public class HospitalItemListener implements OnItemClickListener <Hospital>{

    private Activity context;

    public HospitalItemListener(Activity context){
        this.context = context;
    }

    @Override
    public void onItemClick(Hospital hospital) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_HOSPITAL, hospital);
        context.startActivity(intent);
    }
}
