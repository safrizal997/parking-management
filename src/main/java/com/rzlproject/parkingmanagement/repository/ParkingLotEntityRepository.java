package com.rzlproject.parkingmanagement.repository;

import com.rzlproject.parkingmanagement.entity.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotEntityRepository extends JpaRepository<ParkingLotEntity, Integer> {

    List<ParkingLotEntity> findByIsAvailableTrue();

    ParkingLotEntity findByParkingLotIgnoreCase(String parkingLot);

    @Modifying
    @Query("update ParkingLotEntity p set p.isAvailable = false where p.idParkingLot =?1")
    void setAvailableToFalse(Integer idParkingLot);

    @Modifying
    @Query("update ParkingLotEntity p set p.isAvailable = true where p.idParkingLot =?1")
    void setAvailableToTrue(Integer idParkingLot);

}
