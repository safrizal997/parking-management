package com.rzlproject.parkingmanagement.model;

import com.rzlproject.parkingmanagement.entity.TransactionEntity;

public class ExitVehicleRequest {

    private String platNumber;

    public ExitVehicleRequest() {
    }

    public ExitVehicleRequest(String platNumber) {
        this.platNumber = platNumber;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }
}
