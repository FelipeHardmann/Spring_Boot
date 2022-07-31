package com.main.app.model;

import java.time.LocalDateTime;

public class ParkingSpot {

	private String number;
	private LocalDateTime registrationDate;
	private String responsibleName;
	private String apartment;
	private String block;
	private Car car;
	
	//Encapsulando atributos
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		number = number;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	//Construindo e sobrecarregando
	
	public ParkingSpot() { }
	
	public ParkingSpot(String number, LocalDateTime registrationDate, String responsibleName, String apartment, String block) {
		this.number = number;
		this.registrationDate = registrationDate;
		this.responsibleName = responsibleName;
		this.apartment = apartment;
		this.block = block;
	}
	
}
