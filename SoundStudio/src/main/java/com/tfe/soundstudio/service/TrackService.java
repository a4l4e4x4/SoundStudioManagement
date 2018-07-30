/**
 * 
 */
package com.tfe.soundstudio.service;

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
	
	@Transactional (readOnly=true)
	public Track findByNumber(Integer number) {
		Track result = trackRepo.findByNumber(number);
		
		return result;
	}
	
	@Transactional(readOnly=true)
	public TrackObject findByWave (String wave) {
		TrackObject result = trackObjectRepo.findByWave(wave);
		
		return result;
	}
}
