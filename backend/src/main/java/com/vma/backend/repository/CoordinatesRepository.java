package com.vma.backend.repository;

import com.vma.backend.entity.CoordinatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Integer> {

	List<CoordinatesEntity> getCoordinatesEntitiesByVehicleId(Integer id);
}
