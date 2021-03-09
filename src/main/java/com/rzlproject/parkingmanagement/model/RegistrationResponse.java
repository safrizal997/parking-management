package com.rzlproject.parkingmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RegistrationResponse {

    private String platNumber;
    private String parkingLot;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateEntrance;

    public RegistrationResponse() {
    }

    public RegistrationResponse(String platNumber, String parkingLot, Date dateEntrance) {
        this.platNumber = platNumber;
        this.parkingLot = parkingLot;
        this.dateEntrance = dateEntrance;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Date getDateEntrance() {
        return dateEntrance;
    }

    public void setDateEntrance(Date dateEntrance) {
        this.dateEntrance = dateEntrance;
    }
}



