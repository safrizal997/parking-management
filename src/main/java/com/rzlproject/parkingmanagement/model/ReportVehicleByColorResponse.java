package com.rzlproject.parkingmanagement.model;

import java.util.List;

public class ReportVehicleByColorResponse {

    List<String> platNumber;

    public ReportVehicleByColorResponse(List<String> platNumber) {
        this.platNumber = platNumber;
    }

    public List<String> getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(List<String> platNumber) {
        this.platNumber = platNumber;
    }
}
