package com.vma.backend.service;

import com.vma.backend.entity.CoordinatesEntity;
import com.vma.backend.entity.NotificationEntity;
import com.vma.backend.entity.VehicleEntity;
import com.vma.backend.repository.CoordinatesRepository;
import com.vma.backend.repository.NotificationRepository;
import com.vma.backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VehicleService {

	@Autowired
	private CoordinatesRepository coordinatesRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	private VehicleEntity createVehicle() {
		VehicleEntity vehicle = new VehicleEntity();
		return vehicleRepository.save(vehicle);
	}

	private VehicleEntity getVehicleById(Integer vehicleId) {
		return vehicleRepository.findById(vehicleId).get();
	}

	private NotificationEntity createNotification(Integer vehicleId, String message) {
		NotificationEntity notification = new NotificationEntity();
		notification.setMessage(message);
		notification.setVehicleId(vehicleId);
		notification.setTimestamp(new Date());
		return notificationRepository.save(notification);
	}

	private List<NotificationEntity> getNotificationByVehicleId(Integer vehicleId) {
		return notificationRepository.getNotificationEntitiesByVehicleId(vehicleId);
	}

	private CoordinatesEntity updateVehicleCoordinates(Integer vehicleId, double latitude, double longitude) {
		CoordinatesEntity coordinates = new CoordinatesEntity();
		coordinates.setLatitude(latitude);
		coordinates.setLongitude(longitude);
		coordinates.setTimestamp(new Date());
		coordinates.setVehicleId(vehicleId);

		return coordinatesRepository.save(coordinates);
	}

	private List<CoordinatesEntity> getCoordinatesByVehicleId(Integer vehicleId) {
		return coordinatesRepository.getCoordinatesEntitiesByVehicleId(vehicleId);
	}
}