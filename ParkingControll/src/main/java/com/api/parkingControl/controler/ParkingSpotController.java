package com.api.parkingControl.controler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingControl.dtos.ParkingSpotDto;
import com.api.parkingControl.model.ParkingSpotModel;
import com.api.parkingControl.services.ParkingSpotService;

@RestController //Controlador 
@CrossOrigin(origins = "x", maxAge = 3600) //Poder ser acessado de qualquer lugar
@RequestMapping("/parking-spot") //URI
public class ParkingSpotController {
	
	@Autowired //Ponto de injeção
	ParkingSpotService parkingSpotService;

	@PostMapping //Método Post
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
		/*Criando um ResponseEntity de Objetos, que recebe como parâmetro o dto, utilizando das notações RequestBody para
		receber os dados via JSON e o Valid para validação dos dados.*/
		
		//Verificações criadas pela regra de negócio.
		//OBS: Para ampliar a maturidade da aplicação, criar uma classe somnete para essas verificações
		
		if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro. License Plate Car is already in use");
		}
		
		if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro. Parking spot is already in use");
		}
		
		if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro. Parking spot is already in use for this apartment and block");
		}
		
		var parkingSpotModel = new ParkingSpotModel(); //Instanciando o model utilizando o var
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); //Converter dto para model antes de salvar
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); // Setando o local time*/
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
		/* Retornando uma resposta http, utilizando o ResponseEntity, com o status Created (201) e no corpo, 
		o model que foi salvo no service*/
		
}
	
	
	//Método que retorna todos os registros no banco
	
	@GetMapping
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { 
		//O método retorna uma lista de modelos pelo ResponseEntity
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
		//Retornando o staus Ok (200) e no corpo retorna todos os registros através do método findAll originário do jpa
	}	


	
	@GetMapping("/{id}") //Método get que recebe como parâmetro o id para retornar todo o registro
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
		// PathVariable para receber o id passado na url, value tem que ser igual ao nome referenciado na url, e o tipo
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		//Utilizando o optional para evitar o NullPointerException
		if(!parkingSpotModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not Found");
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());	
		}


	@DeleteMapping("/{id}") //Método Delete que recebe como parâmetro o id para deletar
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) { //Método semelhante ao de procurar pelo id
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not Found");
		parkingSpotService.delete(parkingSpotModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Register deleted successfully");
		
	}

	@PutMapping("/{id}") //Método update que recebe como parâmetro o id do registro para ser atualizado
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
		/* Método updateParkingSpot que retorna um ResponseEntity com o Object, pois o modelo pode existir no banco ou não.
		 * Recebe como parâmetro um PathVariable que recebe o id do registro que será atualizado cujo foi passado no endpoint  
		 * e, como Json (RequestBody) o modelo com os novos dados para ser salvo (em formato Dto), utilizando a notação Valid para fazer 
		 * valer as validações no Dto.*/
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
		//Verifica se o registro existe no banco através do Optional para evitar o NullPointerException. Utilizando método nativo do Optional
		//A verificação é feita utilizando o método já criado que faz buscas pelo id e é passado o id recebido no PathVariable como parâmetro 
		
		
		/*var parkingSpotModel = parkingSpotModelOptional.get(); //Instanciando o modelo
		parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
		parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
		parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
		parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
		parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
		parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
		parkingSpotModel.setApartment(parkingSpotDto.getApartment());
		parkingSpotModel.setBlock(parkingSpotDto.getBlock());*/
		//Utilizando dos métodos Sets e passando como parâmetro os novos dados que vieram do Json e foram salvos na instância do Dto.
		//Esses parâmetros são passados através do método get.
		//OBS: Notar que Id e data de registro não são atualizados.
		
		var parkingSpotModel = new ParkingSpotModel(); //Instanciando a classe modelo
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModelOptional);
		//Utilizando do BeansUtils para converter o modelo recebido via Json no modelo optional (já verificado se existe pelo id)
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId()); //Setando o mesmo Id do registro recebido no optional
		parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate()); //Setando a data
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
		//Após a alteração dos dados é utilizado do método save já criado para salvar novamente no banco
	}
	
	
}
