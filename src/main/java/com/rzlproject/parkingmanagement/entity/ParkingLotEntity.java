package com.rzlproject.parkingmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_parking_lot")
public class ParkingLotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parking_lot")
    private Integer idParkingLot;

    @Column(name = "parking_lot")
    private String parkingLot;

    @Column(name = "is_available")
    private boolean isAvailable;


    public ParkingLotEntity() {
    }

    public ParkingLotEntity(String parkingLot, boolean isAvailable) {
        this.parkingLot = parkingLot;
        this.isAvailable = isAvailable;
    }

    public Integer getIdParkingLot() {
        return idParkingLot;
    }

    public void setIdParkingLot(Integer idParkingLot) {
        this.idParkingLot = idParkingLot;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
