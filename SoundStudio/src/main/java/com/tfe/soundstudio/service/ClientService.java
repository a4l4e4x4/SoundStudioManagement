/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;

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
	
	public void saveClient(Client client) {
		clientRepo.save(client);
	}

}
