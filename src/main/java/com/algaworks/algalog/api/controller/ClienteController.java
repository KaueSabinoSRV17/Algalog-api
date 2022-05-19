package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository cr;
	
	@Autowired
	private ClienteService cs;

	public ClienteController(ClienteRepository cr) {
		super();
		this.cr = cr;
	}

	@GetMapping
	public List<Cliente> listar() {
		
		return cr.findAll();
		
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> searchForAnSpecificClientById(@PathVariable Long clienteId) {
		
		return cr.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente savesANewClientOnTheDataBase(@Valid @RequestBody Cliente cliente) {
		
		return cs.salvesANewClient(cliente);
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> updateAnExistingClient(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		
		if (!cr.existsById(clienteId)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		cliente.setId(clienteId);
		cliente = cr.save(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletesAnExistingClient(@PathVariable Long clienteId) {
		
		if (!cr.existsById(clienteId)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		cs.deletesAnExistingClientById(clienteId);
		
		return ResponseEntity.noContent().build();
		
	}

}
