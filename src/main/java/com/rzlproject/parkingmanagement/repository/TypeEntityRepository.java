package com.rzlproject.parkingmanagement.repository;

import com.rzlproject.parkingmanagement.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEntityRepository extends JpaRepository<TypeEntity, Integer> {

    TypeEntity findByTypeCarIgnoreCase(String Type);
   // TypeEntity findByTypeCarIgnoreCase(Integer price);

}
