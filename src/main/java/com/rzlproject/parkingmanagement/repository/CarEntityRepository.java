package com.rzlproject.parkingmanagement.repository;

import com.rzlproject.parkingmanagement.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface CarEntityRepository extends JpaRepository<CarEntity, Integer> {

    @Query("select c.platNumber from CarEntity c where c.platNumber =?1")
    CarEntity findByPlatNumber(String platNumber);

//    @Query(value = "select t.type_car from tbl_car_entity c join tbl_type_car t on (t.id_type = c.fk_type_car) = 1?", nativeQuery = true)
//    CarEntity findTypeCar(String typeCar);
}
