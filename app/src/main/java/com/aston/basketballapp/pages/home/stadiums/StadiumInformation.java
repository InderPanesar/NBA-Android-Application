package com.aston.basketballapp.pages.home.stadiums;

import com.google.android.gms.maps.model.LatLng;

public class StadiumInformation {

    String teamName;
    String stadiumName;
    String capacity;
    String ticketsURL;
    String stadiumURL;
    float latitude;
    float longitude;
    int vectorPointer;


    public StadiumInformation(
            String _teamName,
            String _stadiumName,
            String _capacity,
            String _ticketsURL,
            String _stadiumURL,
            float _latitude,
            float _longitude,
            int _vectorPointer) {

        this.teamName = _teamName;
        this.stadiumName = _stadiumName;
        this.capacity = _capacity;
        this.ticketsURL = _ticketsURL;
        this.stadiumURL = _stadiumURL;
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.vectorPointer = _vectorPointer;

    }


    public String getTeamName() {
        return teamName;
    }
    public String getStadiumName() {
        return stadiumName;
    }
    public String getStadiumURL() {
        return stadiumURL;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getTicketsURL() {
        return ticketsURL;
    }

    public LatLng getMapPosition() {
       return new LatLng(latitude, -longitude);
    }

    public int getVectorPointer() {
        return vectorPointer;
    }

}
