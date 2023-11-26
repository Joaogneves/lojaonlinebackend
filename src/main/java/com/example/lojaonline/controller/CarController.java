	package com.example.lojaonline.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.lojaonline.entity.cliente.Cliente;
import com.example.lojaonline.service.ClienteService;
import com.example.lojaonline.service.Pdf;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.entity.car.Car;
import com.example.lojaonline.entity.car.CarOptionals;
import com.example.lojaonline.entity.car.CarPicture;
import com.example.lojaonline.entity.car.dto.CarCard;
import com.example.lojaonline.service.CarService;

@RestController
@RequestMapping(value = "/cars")
@CrossOrigin(origins = "*")
public class CarController {

	private final String DOWNLOAD_PATH = "src/downloads/";

	@Autowired
	private CarService service;

	@Autowired
	private ClienteService cService;
	
	//POST
	@PostMapping
	public ResponseEntity<Car> registerCar(@RequestBody Car car){
		try {
			service.registerCar(car);
			return ResponseEntity.status(HttpStatus.CREATED).body(car);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	@PostMapping(value = "images")
	public ResponseEntity<CarPicture> addImages(@RequestParam("id") UUID id, @RequestBody CarPicture cp) {
		service.addImage(id, cp);
		return ResponseEntity.ok().body(cp);
	}
	
	@PostMapping(value = "optionals")
	public ResponseEntity<CarOptionals> addOptionals(@RequestParam("id") UUID id, @RequestBody CarOptionals op) {
		service.addOptionals(id, op);
		return ResponseEntity.ok().body(op);
	}

	//GET
	@GetMapping
	public List<CarCard> getAll() {
		return service.getAllCars();
	}
	
	@GetMapping(value = "solds")
	public List<CarCard> getAllSold() {
		return service.getAllCarsSold();
	}
	
	@GetMapping(value = "solds/{id}")
	public List<CarCard> getAllBySeller(@PathVariable UUID id) {
		return service.getAllBySeller(id);
	}
	
	@GetMapping(value = "{id}")
	public Optional<Car> getCarById(@PathVariable UUID id) {
		Optional<Car> car = service.getCarById(id);
		return car;
	}
	
	@GetMapping(value = "images/{id}")
	public List<CarPicture> getImages(@PathVariable UUID id) {
		List<CarPicture> cars = service.getAllImages(id);
		return cars;
	}
	
	@GetMapping(value = "search")
	public List<CarCard> getAllWith(@RequestParam("carName") String carName) {
		return service.getAllwith(carName);
	}
	
	@GetMapping(value = "optionals/{id}")
	public CarOptionals getOptionals(@PathVariable UUID id) {
		return service.getAllOptionals(id);
	}
	
	//PUT
	@PutMapping(value = "/sell")
	public ResponseEntity<Resource> sellCar(@RequestParam UUID carId, @RequestParam UUID userId, @RequestParam UUID clienteId) throws DocumentException, FileNotFoundException, MalformedURLException, FileNotFoundException, MalformedURLException {
		Car car = service.getCarById(carId).orElseThrow();
		Cliente c = cService.findClienteById(clienteId);
		if(!car.getIsSold()) {
			Pdf pdf = new Pdf();
			pdf.createContract(car, c);
			service.sellCar(carId, userId, clienteId);
			Path filePath = Paths.get(DOWNLOAD_PATH, "contract-" + c.getId() + ".pdf");
			Resource resource = new UrlResource(filePath.toUri());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
					.body(resource);
		}

		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
	}
	
	@PutMapping(value = "update-image")
	public ResponseEntity<String> updatePicture(@RequestParam("id") UUID id, @RequestParam("imgurl") String imgUrl) {
		service.updateImage(id, imgUrl);
		return ResponseEntity.ok().body("Ok");
	}
	
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable UUID id, @RequestBody Car car) {
		service.UpdateCar(id, car);
		return ResponseEntity.ok().body(car);
	}
	
	@PutMapping(value = "optionals/update/{id}")
	public ResponseEntity<CarOptionals> updateOpts(@PathVariable UUID id, @RequestBody CarOptionals op ) {
		service.updateOpts(id, op);
		return ResponseEntity.ok().body(op);
	}
	
	//DELETE
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable UUID id) {
		Car car = service.getCarById(id).orElseThrow();
		if(!car.getIsDeleted()) {
			service.deleteCar(id);
			return ResponseEntity.status(HttpStatus.OK).body(car);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
	}
	
	@DeleteMapping(value = "optionals/delete/{id}")
	public ResponseEntity<String> deleteOpts(@PathVariable UUID id) {
		service.deleteOpts(id);
		return ResponseEntity.ok().body("Ok");
	}
}

