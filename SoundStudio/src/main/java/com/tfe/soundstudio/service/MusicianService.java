/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.repository.MusicianRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class MusicianService {
	
	private final MusicianRepo musicianRepo;

	public MusicianService(MusicianRepo musicianRepo) {
		super();
		this.musicianRepo = musicianRepo;
	}
	
	@Transactional
	public void saveMusician(Musician musician) {
		musicianRepo.save(musician, 5);
	}

}
