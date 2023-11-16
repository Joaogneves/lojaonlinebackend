package com.example.lojaonline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lojaonline.entity.cliente.address.ClienteAddress;
import com.example.lojaonline.repository.ClienteAddressRepository;
import com.example.lojaonline.repository.ClienteAddressRepositoryPk;
import com.example.lojaonline.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired 
	private ClienteRepository cRepository;
	
	@Autowired 
	private ClienteAddressRepository aRepository;
	
	@Autowired 
	private ClienteAddressRepositoryPk pkRepository;
	
	public List<ClienteAddress> findAll() { 
		return pkRepository.findAll();
	}
	
	public ClienteAddress findById(UUID id) { 
		return pkRepository.findById(id).orElseThrow();
	}
	
	public void registerClient(ClienteAddress input) {
		cRepository.save(input.getCliente());
		aRepository.save(input.getAddress());
		pkRepository.save(input);
	}
	
	public List<ClienteAddress> findAllNotServed() { 
		List<ClienteAddress> clientes = pkRepository.findAll();
		List<ClienteAddress> notServed = new ArrayList<>();
		for(ClienteAddress c : clientes) {
			if(c.getCliente().getIsServed() != true) {
				notServed.add(c);				
			}
		}
		return notServed;
	}
	
	public void serveClient(UUID id) {
		ClienteAddress c = pkRepository.findById(id).orElseThrow();
		c.getCliente().setIsServed(!c.getCliente().getIsServed());
		pkRepository.save(c);
	}
}
