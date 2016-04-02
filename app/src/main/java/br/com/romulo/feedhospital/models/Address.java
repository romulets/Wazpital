package br.com.romulo.feedhospital.models;

import java.io.Serializable;

/**
 * Created by romul_000 on 25/03/2016.
 */
public class Address implements Serializable{

    private String street;
    private String number;
    private String complement;
    private String reference;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String toString(){
        String address = getStreet();
        address += getNumber() != null ? ", "+getNumber() : "";
        address += getComplement() != null ? ", "+getComplement() : "";
        address += getReference() != null ? ", "+getReference() : "";
        address += getZipcode() != null ? " - "+getZipcode() : "";
        address += ".";

        address += getNeighborhood() != null ? " "+getNeighborhood()+"," : "";
        address += " "+getCity()+" - "+getState()+", "+getCountry()+".";

        return address;
    }
}
