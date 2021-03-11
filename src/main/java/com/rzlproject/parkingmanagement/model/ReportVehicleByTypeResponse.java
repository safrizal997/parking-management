package com.rzlproject.parkingmanagement.model;

public class ReportVehicleByTypeResponse {

    private int totalVehicle;

    public ReportVehicleByTypeResponse(int totalVehicle) {
        this.totalVehicle = totalVehicle;
    }

    public int getTotalVehicle() {
        return totalVehicle;
    }

    public void setTotalVehicle(int totalVehicle) {
        this.totalVehicle = totalVehicle;
    }
}
