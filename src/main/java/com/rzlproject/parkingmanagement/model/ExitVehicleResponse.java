package com.rzlproject.parkingmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExitVehicleResponse {

    private String platNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "GMT+7")
    private Date dateEntrance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "GMT+7")
    private Date dateExit;
    private Integer paidAmount;

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public Date getDateEntrance() {
        return dateEntrance;
    }

    public void setDateEntrance(Date dateEntrance) {
        this.dateEntrance = dateEntrance;
    }

    public Date getDateExit() {
        return dateExit;
    }

    public void setDateExit(Date dateExit) {
        this.dateExit = dateExit;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }
}
