package com.example.lojaonline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lojaonline.entity.car.Car;
import com.example.lojaonline.entity.car.CarOptionals;
import com.example.lojaonline.entity.car.CarPicture;
import com.example.lojaonline.entity.car.dto.CarCard;
import com.example.lojaonline.entity.cliente.Cliente;
import com.example.lojaonline.entity.sale.Sale;
import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.repository.CarOptionalsRepository;
import com.example.lojaonline.repository.CarPictureRepository;
import com.example.lojaonline.repository.CarRepository;
import com.example.lojaonline.repository.ClienteRepository;
import com.example.lojaonline.repository.SaleRepository;
import com.example.lojaonline.repository.UserRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;

	@Autowired
	private CarPictureRepository cprepository;

	@Autowired
	private CarOptionalsRepository corepository;

	@Autowired
	private UserRepository urepository;

	@Autowired
	private SaleRepository srepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<CarCard> getAllCars() {
		List<Car> cars = repository.findAll();
		List<CarCard> carCard = new ArrayList<>();
		for (Car c : cars) {
			if (!c.getIsDeleted() && !c.getIsSold()) {
				CarCard cc = new CarCard();
				cc.setId(c.getId());
				cc.setName(c.getName());
				cc.setPrice(c.getPrice());
				cc.setDescription(c.getDescription());
				List<CarPicture> cp = c.getPictures();
				if (!cp.isEmpty()) {
					cc.setPicture(cp.get(0).getImgUrl() != null ? cp.get(0).getImgUrl() : c.getPicture());
				}

				else {
					cc.setPicture(c.getPicture());
				}
				cc.setCarYear(c.getCarYear());
				cc.setCarBrand(c.getCarBrand());
				cc.setCarColor(c.getCarColor().toString());
				carCard.add(cc);
			}
		}
		return carCard;
	}

	public Optional<Car> getCarById(UUID id) {
		Optional<Car> car = repository.findById(id);
		return car;
	}

	public void registerCar(Car car) {
		repository.save(car);
	}

	public void sellCar(UUID carId, UUID userId, UUID clienteId) {
		Car car = repository.findById(carId).orElseThrow();
		User user = urepository.findById(userId).orElseThrow();
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
		Sale sale = new Sale(car, user, cliente);
		srepository.save(sale);
		car.sellCar();
		repository.save(car);
		cliente.setIsServed(true);
		clienteRepository.save(cliente);
	}

	public void deleteCar(UUID id) {
		Car car = repository.findById(id).orElseThrow();
		car.setIsDeleted(true);
		repository.save(car);
	}

	public void UpdateCar(UUID id, Car car) {
		Car c = repository.findById(id).orElseThrow();
		c.setId(id);
		c.setCarBrand(car.getCarBrand());
		c.setName(car.getName());
		c.setDescription(car.getDescription());
		c.setCarYear(car.getCarYear());
		c.setPrice(car.getPrice());
		c.setFuel(car.getFuel());
		c.setTransmissionType(car.getTransmissionType());
		c.setCarColor(car.getCarColor());
		c.setKm(car.getKm());
		repository.save(c);
	}

	public List<CarCard> getAllCarsSold() {
		List<Sale> sale = srepository.findAll();
		List<CarCard> cars = new ArrayList<>();
		for (Sale s : sale) {
			Car c = s.getCar();
			CarCard cc = new CarCard(c.getId(), c.getCarBrand(), c.getName(), c.getDescription(), c.getPrice(),
					c.getPicture(), c.getCarYear(), c.getCarColor().toString(), s.getCliente().getFullName());
			List<CarPicture> cp = c.getPictures();
			if (!cp.isEmpty()) {
				cc.setPicture(cp.get(0).getImgUrl() != null ? cp.get(0).getImgUrl() : c.getPicture());
			}

			else {
				cc.setPicture(c.getPicture());
			}
			cars.add(cc);
		}
		return cars;
	}

	public List<CarCard> getAllBySeller(UUID id) {
		List<Sale> sale = srepository.findAllBySellerId(id);
		List<CarCard> cars = new ArrayList<>();
		for (Sale s : sale) {
			Car c = s.getCar();
			CarCard cc = new CarCard(c.getId(), c.getCarBrand(), c.getName(), c.getDescription(), c.getPrice(),
					c.getPicture(), c.getCarYear(), c.getCarColor().toString(), s.getCliente().getFullName());
			List<CarPicture> cp = c.getPictures();
			if (!cp.isEmpty()) {
				cc.setPicture(cp.get(0).getImgUrl() != null ? cp.get(0).getImgUrl() : c.getPicture());
			}

			else {
				cc.setPicture(c.getPicture());
			}
			cars.add(cc);
		}
		return cars;
	}

	public List<CarCard> getAllwith(String carName) {
		List<Car> cars = repository.findAll();
		List<CarCard> carCard = new ArrayList<>();
		for (Car c : cars) {
			if (c.getName().toLowerCase().contains(carName.toLowerCase()) && c.getIsSold() == false
					&& c.getIsDeleted() == false) {
				CarCard cc = new CarCard();
				cc.setId(c.getId());
				cc.setName(c.getName());
				cc.setPrice(c.getPrice());
				cc.setDescription(c.getDescription());
				List<CarPicture> cp = c.getPictures();
				if (!cp.isEmpty()) {
					cc.setPicture(cp.get(0).getImgUrl() != null ? cp.get(0).getImgUrl() : c.getPicture());
				}

				else {
					cc.setPicture(c.getPicture());
				}
				carCard.add(cc);
			}
		}
		return carCard;
	}

	public void addImage(UUID id, CarPicture cp) {
		Car car = repository.findById(id).orElseThrow();
		cp.setCar(car);
		cprepository.save(cp);
	}

	public List<CarPicture> getAllImages(UUID id) {
		Car car = repository.findById(id).orElseThrow();
		return car.getPictures();
	}

	public void updateImage(UUID id, String urlImage) {
		Car car = repository.findById(id).orElseThrow();
		car.setPicture(urlImage);
		repository.save(car);
	}

	public void addOptionals(UUID id, CarOptionals op) {
		Car car = repository.findById(id).orElseThrow();
		CarOptionals co = new CarOptionals();
		co.setCar(car);
		co.setAirbags(op.getAirbags());
		co.setElectricWindow(op.getElectricWindow());
		co.setEletricLocks(op.getEletricLocks());
		co.setAlarm(op.getAlarm());
		co.setAirConditioning(op.getAirConditioning());
		co.setHidraulicSteering(op.getHidraulicSteering());
		co.setMultmedia(op.getMultmedia());
		corepository.save(co);
	}

	public CarOptionals getAllOptionals(UUID id) {
		Car car = repository.findById(id).orElseThrow();
		return car.getOptionals();
	}

	public void updateOpts(UUID id, CarOptionals op) {
		CarOptionals co = corepository.findById(id).orElseThrow();
		co.setAirbags(op.getAirbags());
		co.setElectricWindow(op.getElectricWindow());
		co.setEletricLocks(op.getEletricLocks());
		co.setAlarm(op.getAlarm());
		co.setAirConditioning(op.getAirConditioning());
		co.setHidraulicSteering(op.getHidraulicSteering());
		co.setMultmedia(op.getMultmedia());
		corepository.save(co);
	}

	public void deleteOpts(UUID id) {
		try {
			CarOptionals co = corepository.findById(id).orElseThrow();
			corepository.deleteById(co.getId());
		} catch (Exception e) {
			System.out.println("Erro ao excluir: " + e.getMessage());
		}
	}
}
