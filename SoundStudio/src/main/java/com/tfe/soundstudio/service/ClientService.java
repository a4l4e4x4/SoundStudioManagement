/**
 * 
 */
package com.tfe.soundstudio.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.repository.ClientRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class ClientService {
	
	private final ClientRepo clientRepo;

	public ClientService(ClientRepo clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
	
	@Transactional
	public void saveClient(Client client) {
		
		clientRepo.save(client, 5);
	}

	@Transactional
	public void saveAllClients(Iterable<Client> clients) {
		clientRepo.save(clients, 10);
	}
	
	@Transactional
	public Iterable<Client> findAll() {
		Iterable<Client> result = clientRepo.findAll();
		
		return result;
	}

	public Optional<Client> findById(Long id) {
		
		Optional<Client> result = clientRepo.findById(id);
	
		return result;
	}
	
	

}
