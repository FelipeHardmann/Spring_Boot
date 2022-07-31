package com.api.parkingControl.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Entidade do db
@Table(name = "TB_PARKING_SPOT") // Tabela do banco
public class ParkingSpotModel implements Serializable{ //Converte objetos Java em bytes para ser salvo no banco
	private static final long serialVersionUID = 1L;
	
	
	@Id //Id da entidade
	@GeneratedValue(strategy = GenerationType.AUTO) //Valor do id será gerado automaticamente 
	private UUID id;
	@Column(nullable = false, unique = true, length = 10)
	private String parkingSpotNumber;
	@Column(nullable = false, unique = true, length = 7) //Coluna da tabela, não pode ser nula, unica, string com 7 char
	private String licensePlateCar;
	@Column(nullable = false, unique = true, length = 70)
	private String brandCar;
	@Column(nullable = false, unique = true, length = 70)
	private String modelCar;
	@Column(nullable = false, length = 70)
	private String colorCar;
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	@Column(nullable = false, length = 130)
	private String responsibleName;
	@Column(nullable = false, length = 30)
	private String apartment;
	@Column(nullable = false, length = 30)
	private String block;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}
	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}
	public String getLicensePlateCar() {
		return licensePlateCar;
	}
	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
	}
	public String getBrandCar() {
		return brandCar;
	}
	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public String getColorCar() {
		return colorCar;
	}
	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
