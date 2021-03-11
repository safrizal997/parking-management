package com.rzlproject.parkingmanagement.repository;

import com.rzlproject.parkingmanagement.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeEntityRepository extends JpaRepository<TypeEntity, Integer> {

    Optional<TypeEntity> findByTypeCarIgnoreCase(String Type);

}
