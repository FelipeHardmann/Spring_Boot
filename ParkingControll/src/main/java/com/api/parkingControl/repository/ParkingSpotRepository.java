package com.api.parkingControl.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingControl.model.ParkingSpotModel;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
	//Extendendo o jpa pois ele traz diversos métodos prontos para utilizar em transações com o db. Passando entre <> o modelo e o id
	
	boolean existsByLicensePlateCar(String licensePlateCar);
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	boolean existsByApartmentAndBlock(String apartment, String block);

	

}
