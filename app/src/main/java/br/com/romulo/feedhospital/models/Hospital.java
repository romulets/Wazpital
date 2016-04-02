package br.com.romulo.feedhospital.models;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.romulo.feedhospital.R;

/**
 * Created by romul_000 on 19/03/2016.
 */
public class Hospital implements Serializable{

    private String name;

    private String descrpition;

    private String imageURL;

    private HospitalState state;

    private HospitalState votedState;

    private Address address;

    private ArrayList<Contact> contacts;

    public Hospital() {
        this.contacts = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrpition() {
        return descrpition;
    }

    public void setDescrpition(String descrpition) {
        this.descrpition = descrpition;
    }

    public HospitalState getState() {
        return this.state;
    }

    public HospitalState getVotedState() {
        return votedState;
    }

    public void setVotedState(HospitalState votedState) {
        this.votedState = votedState;
    }

    public void setState(HospitalState state) {
        this.state = state;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }


}
