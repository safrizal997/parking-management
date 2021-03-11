package com.rzlproject.parkingmanagement.repository;

import com.rzlproject.parkingmanagement.entity.TransactionEntity;
import com.rzlproject.parkingmanagement.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {

    Optional<TransactionEntity> findByPlatNumberAndIsOutFalse(String platNumber);

    TransactionEntity findByTypeCarAndPlatNumber(TypeEntity typeCar, String platNumber);

    int countTransactionEntityByTypeCar(TypeEntity typeCar);

    List<TransactionEntity> findByColorIgnoreCase(String color);

}
