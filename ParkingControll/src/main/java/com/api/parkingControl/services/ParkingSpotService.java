package com.api.parkingControl.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingControl.model.ParkingSpotModel;
import com.api.parkingControl.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	@Autowired //Ponto de injeção de dependência
	ParkingSpotRepository parkingSpotRepository;
	
	@Transactional //Garantir que não ocorram "dados quebrados"
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}

	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}
	
	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}
	
	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}
	
	
	public Page<ParkingSpotModel> findAll(Pageable pageable) {
		return parkingSpotRepository.findAll(pageable);
	}
	
	
	public Optional<ParkingSpotModel> findById(UUID id) { //Método do jpa que retorna um registro pelo id
		return parkingSpotRepository.findById(id);
	}

	@Transactional
	public void delete(ParkingSpotModel parkingSpotModel) { //Método jpa que deleta
		parkingSpotRepository.delete(parkingSpotModel);
	}

	
}
