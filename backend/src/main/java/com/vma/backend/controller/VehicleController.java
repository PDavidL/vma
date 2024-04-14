package com.vma.backend.controller;

import com.vma.backend.dto.VehicleInRadiusDto;
import com.vma.backend.entity.CoordinatesEntity;
import com.vma.backend.entity.NotificationEntity;
import com.vma.backend.entity.VehicleEntity;
import com.vma.backend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;


	@GetMapping("/vehicles")
	public ResponseEntity<VehicleInRadiusDto> getVehiclesInRadius(@RequestParam("latitude") double latitude,
			@RequestParam("longitude") double longitude,
			@RequestParam("radius") double radius) {
		return ResponseEntity.ok(new VehicleInRadiusDto(vehicleService.getVehiclesInRadius(latitude, longitude, radius)));
	}

	@PostMapping("/vehicles")
	public ResponseEntity<VehicleEntity> registerVehicle() {
		return ResponseEntity.ok(vehicleService.registerVehicle());
	}

	@PostMapping("/vehicle/{id}")
	public ResponseEntity<VehicleEntity> updateVehiclePosition(@PathVariable(name = "id") Integer vehicleId, @RequestBody CoordinatesEntity coordinates) {
		vehicleService.updateVehicleCoordinates(vehicleId, coordinates);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/allVehicles")
	public ResponseEntity<List<VehicleEntity>> getVehiclesInRadius() {
		return ResponseEntity.ok(vehicleService.getAllVehicles());
	}


	@GetMapping("/vehicleCoordinates")
	public ResponseEntity<List<CoordinatesEntity>> getVehicleCoordinates(@RequestParam("vehicleId") Integer vehicleId) {
		return ResponseEntity.ok(vehicleService.getCoordinatesByVehicleId(vehicleId));
	}
	@PostMapping("/notifications")
	public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notificationEntity) {
		vehicleService.createNotification(notificationEntity);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/latestNotification")
	public ResponseEntity<NotificationEntity> getLatestNotification(@RequestParam("vehicleId") Integer vehicleId) {
		return ResponseEntity.ok(vehicleService.getLatestNotificationByVehicleId(vehicleId));
	}
}
