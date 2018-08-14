/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;

import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.repository.EngineerRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class EngineerService {

	private final EngineerRepo engineerRepo;

	public EngineerService(EngineerRepo engineerRepo) {
		super();
		this.engineerRepo = engineerRepo;
	}
	
	
	public void saveEngineer (Engineer engineer) {
		engineerRepo.save(engineer, 5);
	}


	public Iterable<Engineer> findAll() {
		Iterable<Engineer> result = engineerRepo.findAll();
		return result;
	}
}
