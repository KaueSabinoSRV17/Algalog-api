package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		Cliente c1 = new Cliente();

		c1.setId(1);
		c1.setEmail("kauevillaca2018@gmail.com");
		c1.setName("Joao");
		
		Cliente c2 = new Cliente();

		c2.setId(2);
		c2.setEmail("anavillaca2018@gmail.com");
		c2.setName("aana");
		
		
		return Arrays.asList(c1, c2);
		
	}

}
