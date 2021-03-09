package com.rzlproject.parkingmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_car_entity")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Integer idCar;

    @Column(name = "plat_number", nullable = false)
    private String platNumber;

    @Column(name = "color_car")
    private String color;

    @ManyToOne
    @JoinColumn(name = "fk_type_car",referencedColumnName = "id_type")
    private TypeEntity typeCar;



    public CarEntity() {
    }

    public CarEntity(String platNumber, String color, TypeEntity typeCar) {
        this.platNumber = platNumber;
        this.color = color;
        this.typeCar = typeCar;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
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

    public TypeEntity getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeEntity typeCar) {
        this.typeCar = typeCar;
    }
}
