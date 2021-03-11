package com.rzlproject.parkingmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Integer idCar;

    @Column(name = "plat_number")
    private String platNumber;

    @Column(name = "color_car")
    private String color;

    @ManyToOne
    @JoinColumn(name = "fk_type_car",referencedColumnName = "id_type")
    private TypeEntity typeCar;

    @Column(name = "date_entrance")
    private Date dateEntrance;

    @Column(name = "date_exit")
    private Date dateExit;

    @Column(name = "paid_amount")
    private Integer paidAmount;

    @Column(name = "is_out")
    private boolean isOut = false;



    public TransactionEntity() {
    }

    public TransactionEntity(String platNumber, String color, TypeEntity typeCar) {
        this.platNumber = platNumber;
        this.color = color;
        this.typeCar = typeCar;
    }

    public TransactionEntity(Date dateEntrance, Date dateExit, Integer paidAmount, boolean isOut) {
        this.dateEntrance = dateEntrance;
        this.dateExit = dateExit;
        this.paidAmount = paidAmount;
        this.isOut = isOut;
    }

    public TransactionEntity(Date dateEntrance){
        this.dateEntrance = dateEntrance;
    }

    public TransactionEntity(String platNumber){
        this.platNumber = platNumber;
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

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }
}
