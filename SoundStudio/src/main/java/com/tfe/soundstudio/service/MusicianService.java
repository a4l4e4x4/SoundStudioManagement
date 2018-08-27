/**
 * 
 */
package com.tfe.soundstudio.service;

import java.util.Optional;

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
	
	@Transactional(readOnly=true)
	public Iterable<Musician> findAll(){
		Iterable<Musician> result = musicianRepo.findAll();
		
		return result;
	}
	
	@Transactional
	public void saveMusician(Musician musician) {
		musicianRepo.save(musician, 5);
		
	}

	public void saveAllMusicians(Iterable<Musician> musicians) {
		musicianRepo.save(musicians,  5);
		
	}

	public Musician findById(Long id) {
		Musician result = musicianRepo.findById(id, 4).orElseThrow(()->new RuntimeException("No such musician"));
		
		return result;
	}

	public void deleteById(Long id) {
		musicianRepo.deleteById(id);

	}

}
