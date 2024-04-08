package com.vma.backend.repository;

import com.vma.backend.entity.CoordinatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends CrudRepository<CoordinatesEntity, Integer> {
}
