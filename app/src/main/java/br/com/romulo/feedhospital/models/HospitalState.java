package br.com.romulo.feedhospital.models;

import br.com.romulo.feedhospital.R;

/**
 * Created by romul_000 on 19/03/2016.
 */
public enum HospitalState {
    RECOMMENDED(0),
    COMPLICATED(1),
    BAD(2);

    private final int value;

    HospitalState(int value) {
        this.value = value;
    }

    public int getStringResource() {
        switch (this) {
            case RECOMMENDED:
                return R.string.hospital_state_recommended;
            case COMPLICATED:
                return R.string.hospital_state_complicated;
            case BAD:
                return R.string.hospital_state_bad;
            default:
                return R.string.hospital_state_bad;
        }
    }

    public int getValue(){
        return value;
    }

    public static HospitalState fromInt(int hospitalState) {
        switch (hospitalState){
            case 0:
                return RECOMMENDED;
            case 1:
                return COMPLICATED;
            default:
                return BAD;
        }
    }
}
