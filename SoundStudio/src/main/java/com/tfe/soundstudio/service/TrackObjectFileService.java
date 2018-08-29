/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.TrackObjectFile;
import com.tfe.soundstudio.repository.TrackObjectFileRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class TrackObjectFileService {
	
	private final TrackObjectFileRepo trackObjectFileRepo;
	

	
	public TrackObjectFileService(TrackObjectFileRepo trackObjectFileRepo) {
		super();
		this.trackObjectFileRepo = trackObjectFileRepo;
	}



	@Transactional
	public TrackObjectFile findById(Long id) {
		
		TrackObjectFile trackObjectFile = trackObjectFileRepo.findById(id, 4).orElseThrow(()-> new RuntimeException ("no such file"));
		
		return trackObjectFile;
	}
	
	public void saveTrackObjectFile(TrackObjectFile tof) {
		trackObjectFileRepo.save(tof, 10);
	}
}
