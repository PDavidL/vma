package com.vma.backend.dto;

import com.vma.backend.entity.VehicleEntity;

import java.util.List;

public class VehicleInRadiusDto {

	private List<VehicleEntity> vehicles;

	public VehicleInRadiusDto() {
	}

	public VehicleInRadiusDto(List<VehicleEntity> vehicles) {
		this.vehicles = vehicles;
	}

	public List<VehicleEntity> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleEntity> vehicles) {
		this.vehicles = vehicles;
	}

}
