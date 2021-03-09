package com.rzlproject.parkingmanagement.model;

public class RegistrationRequest {
    private String platNumber;
    private String color;
    private String type;

    public RegistrationRequest(String platNumber, String color, String type) {
        this.platNumber = platNumber;
        this.color = color;
        this.type = type;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
