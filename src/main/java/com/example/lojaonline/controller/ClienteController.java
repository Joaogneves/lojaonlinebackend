package com.example.lojaonline.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.entity.cliente.Cliente;
import com.example.lojaonline.entity.cliente.address.ClienteAddress;
import com.example.lojaonline.service.ClienteService;
import com.example.lojaonline.service.Pdf;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	private final String DOWNLOAD_PATH = "src/downloads/";
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteAddress>> findAll() {
		List<ClienteAddress> clientes = service.findAll();
		return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteAddress> findById(@PathVariable UUID id) {
		ClienteAddress ca =  service.findById(id);
		return ResponseEntity.ok().body(ca);
	}
	
	
	@GetMapping(value = "/pdf/{id}")
	public ResponseEntity<Resource> printPdf(@PathVariable UUID id) throws DocumentException, FileNotFoundException, MalformedURLException {
		ClienteAddress ca =  service.findById(id);
		Pdf pdf = new Pdf();
		pdf.create(ca);
		Path filePath = Paths.get(DOWNLOAD_PATH, "Ficha-" + id + ".pdf");
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
		
	}
	
	@GetMapping(value = "/notServed")
	public ResponseEntity<List<ClienteAddress>> findAllNotServed() {
		List<ClienteAddress> clientes = service.findAllNotServed();
		return ResponseEntity.ok().body(clientes);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registerClient(@RequestBody ClienteAddress input) {
		System.out.print("Esse é o cliente " + input.getCliente());
		try {
			service.registerClient(input);
			return ResponseEntity.status(HttpStatus.CREATED).body(input.getCliente());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteAddress> serveCliente(@PathVariable UUID id) {
		service.serveClient(id);
		return ResponseEntity.ok().body(null);
	}
	
}
