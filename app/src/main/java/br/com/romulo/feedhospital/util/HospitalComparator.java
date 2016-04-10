package br.com.romulo.feedhospital.util;

import java.util.Comparator;

import br.com.romulo.feedhospital.models.Hospital;

/**
 * Created by romul_000 on 10/04/2016.
 */
public class HospitalComparator implements Comparator<Hospital> {

    @Override
    public int compare(Hospital hospital1, Hospital hospital2)
    {

        return hospital1.getState().getValue() - hospital2.getState().getValue();
    }

}
