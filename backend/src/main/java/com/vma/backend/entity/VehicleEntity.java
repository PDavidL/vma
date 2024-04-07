package com.vma.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Vehicle")
public class VehicleEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToMany(mappedBy = "vehicle")
	private List<CoordinatesEntity> coordinates;

	@OneToMany(mappedBy = "vehicle")
	private List<MessageEntity> messages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CoordinatesEntity> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<CoordinatesEntity> coordinates) {
		this.coordinates = coordinates;
	}

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
}
