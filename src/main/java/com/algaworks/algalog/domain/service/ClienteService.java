package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.exception.BusinessException;
import com.algaworks.algalog.domain.model.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;
	
	@Transactional
	public Cliente salvesANewClient(Cliente cliente) {
		
		boolean emailAlreadyInUse = cr.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailAlreadyInUse) {
			
			throw new BusinessException("Já existe um cliente cadastrado com este email!"); 
			
		}
		
		return cr.save(cliente);
		
	}
	
	@Transactional
	public void deletesAnExistingClientById(Long clientId) {
		
		cr.deleteById(clientId);
		
	}

}
