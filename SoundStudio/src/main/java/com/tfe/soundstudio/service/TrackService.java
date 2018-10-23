/**
 * 
 */
package com.tfe.soundstudio.service;

import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.model.TrackObject;
import com.tfe.soundstudio.repository.TrackObjectRepo;
import com.tfe.soundstudio.repository.TrackRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class TrackService {
	
	private final TrackRepo trackRepo;
	private final TrackObjectRepo trackObjectRepo;

	public TrackService (TrackRepo trackRepo, TrackObjectRepo trackObjectRepo) {
		super();
		this.trackRepo = trackRepo;
		this.trackObjectRepo = trackObjectRepo;
	}
	
	
	//for Track
	
	@Transactional (readOnly=true)
	public Track findByID(Long id) {
		Track result = trackRepo.findById(id).orElseThrow(() -> new NotFoundException());
		
		return result;
	} 
	
	@Transactional (readOnly=true)
	public Track findByName(String name) {
		Track result = trackRepo.findByName(name);
		
		return result;
	} 
	
	@Transactional(readOnly=true)
	public TrackObject findByWave (String wave) {
		TrackObject result = trackObjectRepo.findByWave(wave);
	
		return result;
	}
	
	@Transactional
	public void saveTracks(Iterable<Track> tracks) {
		//trackRepo.saveAll(tracks);
		trackRepo.save(tracks, 5);
		//trackRepo.save(tracks, 200);

	}
	
	@Transactional
	public void saveTrack(Track track) {
		trackRepo.save(track);
	}
	
	
	
	
	//for TrackObject
	
	@Transactional
	public void saveTrackObject(TrackObject trackObject) {
		trackObjectRepo.save(trackObject);
	}
	
	@Transactional
	public void saveTrackObjects(Iterable<TrackObject> trackObjects) {
		trackObjectRepo.saveAll(trackObjects);
	}


	@Transactional
	public void deleteAll() {
		trackRepo.deleteAll();
		trackObjectRepo.deleteAll();
		
	}
}
