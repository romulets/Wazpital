package br.com.romulo.feedhospital.util;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.com.romulo.feedhospital.models.Address;
import br.com.romulo.feedhospital.models.Contact;
import br.com.romulo.feedhospital.models.Hospital;
import br.com.romulo.feedhospital.models.HospitalState;

/**
 * Created by romul_000 on 25/03/2016.
 */
public class JsonHospitalLoader {

    private Context context;
    private Location location;

    public JsonHospitalLoader(Context context) {
        this.context = context;
    }

    public JsonHospitalLoader(Context context, Location location) {
        this(context);
        this.location = location;
    }

    public ArrayList<Hospital> load(ArrayList<Hospital> hospitals) {
        Hospital hospital;
        Address address;
        Contact contact;
        JSONArray hospitalData, contactData;
        JSONObject row, addressRow, contactRow;
        int hospitalIndex, contactIndex, hospitalState;

        try{
            hospitalData = new JSONArray(this.getJson());
            for(hospitalIndex = 0; hospitalIndex < hospitalData.length(); hospitalIndex++){
                row = hospitalData.getJSONObject(hospitalIndex);
                addressRow = row.getJSONObject("address");
                contactData = row.getJSONArray("contacts");

                hospital = new Hospital();
                hospital.setName(row.getString("name"));
                hospital.setDescrpition(row.getString("description"));
                hospital.setImageURL(row.getString("imageURL"));
                hospitalState = row.getInt("state");

                switch (hospitalState){
                    case 0:
                        hospital.setState(HospitalState.RECOMMENDED);
                        break;
                    case 1:
                        hospital.setState(HospitalState.COMPLICATED);
                        break;
                    default:
                        hospital.setState(HospitalState.BAD);
                }


                address = new Address();
                address.setStreet(addressRow.getString("street"));

                if(!addressRow.isNull("number")){
                    address.setNumber(addressRow.getString("number"));
                }

                if(!addressRow.isNull("reference")){
                    address.setReference(addressRow.getString("reference"));
                }

                if(!addressRow.isNull("complement")){
                    address.setComplement(addressRow.getString("complement"));
                }

                if(!addressRow.isNull("zipcode")){
                    address.setZipcode(addressRow.getString("zipcode"));
                }

                if(!addressRow.isNull("neighborhood")){
                    address.setNeighborhood(addressRow.getString("neighborhood"));
                }
                
                address.setCity(addressRow.getString("city"));
                address.setState(addressRow.getString("state"));
                address.setCountry(addressRow.getString("country"));
                address.setLatitude(addressRow.getDouble("latitude"));
                address.setLongitude(addressRow.getDouble("longitude"));

                hospital.setAddress(address);

                for(contactIndex = 0; contactIndex < contactData.length(); contactIndex++){
                    contactRow = contactData.getJSONObject(contactIndex);
                    contact = new Contact();
                    contact.setType(contactRow.getString("type"));
                    contact.setContent(contactRow.getString("content"));
                    hospital.addContact(contact);
                }

                rateDistance(hospital);

                hospitals.add(hospital);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return hospitals;
    }

    public ArrayList<Hospital> load() {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        return this.load(hospitals);
    }

    private String getJson(){
        String json;
        try {
            InputStream is = context.getAssets().open("hospitals.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
        return json;
    }


    private void rateDistance(Hospital hospital){
        if(location != null) {
            Log.i("Meu Log", "Latitude: "+location.getLatitude()+" | Longitude: "+location.getLongitude());
        }
        Log.i("Meu Log", "Sem Localidade");
    }
}
