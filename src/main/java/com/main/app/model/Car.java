package com.main.app.model;

public class Car {
	private String licensePlateCar;
	private String brandCar;
	private String modelCar;
	private String colorCar;
	
	//Encapuslando atributos
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
	public String getColorCard() {
		return colorCar;
	}
	public void setColorCard(String colorCar) {
		this.colorCar = colorCar;
	}
	
	
	//Construindo e sobrecarregando
	
	public Car() { }
	
	public Car(String licensePlateCar, String brandCar, String modelCar, String colorCar) {
		this.licensePlateCar = licensePlateCar;
		this.brandCar = brandCar;
		this.modelCar = modelCar;
		this.colorCar = colorCar;
		
	}
	
	
}
