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
import java.util.stream.Collectors;

@Service
public class VehicleService {

	private static final double EARTH_RADIUS_KM = 6371;

	@Autowired
	private CoordinatesRepository coordinatesRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	public VehicleEntity registerVehicle() {
		VehicleEntity vehicle = new VehicleEntity();
		return vehicleRepository.save(vehicle);
	}

	public VehicleEntity getVehicleById(Integer vehicleId) {
		return vehicleRepository.findById(vehicleId).get();
	}

	public NotificationEntity createNotification(NotificationEntity notification) {
		notification.setTimestamp(new Date());
		return notificationRepository.save(notification);
	}

	public List<NotificationEntity> getNotificationByVehicleId(Integer vehicleId) {
		return notificationRepository.getNotificationEntitiesByVehicleId(vehicleId);
	}

	public CoordinatesEntity updateVehicleCoordinates(Integer vehicleId, CoordinatesEntity coordinates) {
		coordinates.setTimestamp(new Date());
		coordinates.setVehicleId(vehicleId);

		return coordinatesRepository.save(coordinates);
	}

	public List<CoordinatesEntity> getCoordinatesByVehicleId(Integer vehicleId) {
		return coordinatesRepository.getCoordinatesEntitiesByVehicleId(vehicleId);
	}

	public List<VehicleEntity> getVehiclesInRadius(double latitude, double longitude, double radius) {
		List<VehicleEntity> vehicleEntities = vehicleRepository.findAll();
		return vehicleEntities.stream().filter(vehicleEntity ->
				isInRadius(latitude, longitude, radius, vehicleEntity.getId())).collect(Collectors.toList());
	}

	/**
	 * Vehicle is in the circle if the distance from the center is less than the radius.
	 * @param centerLatitude
	 * @param centerLongitude
	 * @param radiusInM
	 * @param vehicleId
	 * @return
	 */
	private boolean isInRadius(double centerLatitude, double centerLongitude, double radiusInM, Integer vehicleId) {
		CoordinatesEntity latestCoordinates = coordinatesRepository.findFirstByVehicleIdOrderByTimestampDesc(vehicleId);
		double radiusInKm = 0.001 * radiusInM;
		if (latestCoordinates != null) {
			return calculateDistanceWithHaversine(centerLatitude, centerLongitude, latestCoordinates.getLatitude(), latestCoordinates.getLongitude()) < radiusInKm;
		}
		return false;
	}

	/**
	 * Applying haversine formula to calculate the distance between the given center and the vehicle.
	 * d = 2 * R * arsin( sqrt((1 - cos(vlat - clat) + cos(clat) * cos(vlat) * (1 - cos (vlon - clon)) ) / 2))
	 * using the following equation for simplifying the formula: 1 - cos(x) = 2 sin^2(x/2)
	 *
	 * @param centerLatitude
	 * @param centerLongitude
	 * @param vehicleLatitude
	 * @param vehicleLongitude
	 * @return
	 */
	private double calculateDistanceWithHaversine(double centerLatitude, double centerLongitude, double vehicleLatitude, double vehicleLongitude) {

		double centerLatitudeInRadian = Math.toRadians(centerLatitude);
		double vehicleLatitudeInRadian = Math.toRadians(vehicleLatitude);

		double latDiffInRadian = Math.toRadians(vehicleLatitude - centerLatitude);
		double lonDiffInRadian = Math.toRadians(vehicleLongitude - centerLongitude);
		double partialHaversineFormula = haversine(latDiffInRadian) + Math.cos(centerLatitudeInRadian) * Math.cos(vehicleLatitudeInRadian) * haversine(lonDiffInRadian);

		return 2 * EARTH_RADIUS_KM * Math.asin(Math.sqrt(partialHaversineFormula));
	}

	/**
	 * calculate the haversine of a value.
	 * @param value
	 * @return
	 */
	private double haversine(double value) {
		return Math.pow(Math.sin(value / 2), 2);
	}

}
