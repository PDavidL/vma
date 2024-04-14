package com.vma.backend.dto;

public class VehicleDto {

	private Integer id;

	private Double positionLat;

	private Double positionLng;

	private String notification;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPositionLat() {
		return positionLat;
	}

	public void setPositionLat(Double positionLat) {
		this.positionLat = positionLat;
	}

	public Double getPositionLng() {
		return positionLng;
	}

	public void setPositionLng(Double positionLng) {
		this.positionLng = positionLng;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
}
