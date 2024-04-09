package com.vma.backend.repository;

import com.vma.backend.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

	List<NotificationEntity> getNotificationEntitiesByVehicleId(Integer id);
}
