/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.RecSession;
import com.tfe.soundstudio.model.SessionFile;
import com.tfe.soundstudio.repository.ClientRepo;
import com.tfe.soundstudio.repository.EngineerRepo;
import com.tfe.soundstudio.repository.InstrumentRepo;
import com.tfe.soundstudio.repository.MusicianRepo;
import com.tfe.soundstudio.repository.PieceRepo;
import com.tfe.soundstudio.repository.ProjectRepo;
import com.tfe.soundstudio.repository.RecSessionRepo;
import com.tfe.soundstudio.repository.SessionFileRepo;
import com.tfe.soundstudio.repository.TrackObjectRepo;
import com.tfe.soundstudio.repository.TrackRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class RecSessionService {
	
	private final SessionFileRepo sessionFileRepo;
	private final RecSessionRepo recSessionRepo;
	private final ProjectRepo projectRepo;
	private final MusicianRepo musicianRepo;
	private final EngineerRepo engineerRepo;
	private final ClientRepo clientRepo;
	private final PieceRepo pieceRepo;
	private final InstrumentRepo instRepo;
	private final TrackRepo trackRepo;
	private final TrackObjectRepo trackObjectRepo;
	
	public RecSessionService(SessionFileRepo sessionFileRepo, ProjectRepo projectRepo, MusicianRepo musicianRepo,
			EngineerRepo engineerRepo, ClientRepo clientRepo, PieceRepo pieceRepo, InstrumentRepo instRepo,
			TrackRepo trackRepo, TrackObjectRepo trackObjectRepo, RecSessionRepo recSessionRepo) {
		super();
		this.sessionFileRepo = sessionFileRepo;
		this.projectRepo = projectRepo;
		this.musicianRepo = musicianRepo;
		this.engineerRepo = engineerRepo;
		this.clientRepo = clientRepo;
		this.pieceRepo = pieceRepo;
		this.instRepo = instRepo;
		this.trackRepo = trackRepo;
		this.trackObjectRepo = trackObjectRepo;
		this.recSessionRepo = recSessionRepo;
	}
	
	@Transactional
	public void createRecSession(RecSession recSession) {
		recSessionRepo.save(recSession, 50);
	}
	
	public SessionFile getSessionFileById(Long id) {
		SessionFile result = sessionFileRepo.findById(id, 5).orElseThrow(()->new RuntimeException("No such sessionfile"));
		return result;
	}

	public RecSession findById(Long id) {
		RecSession result = recSessionRepo.findById(id).orElseThrow(()->new RuntimeException("No such recsession"));
		return result;
	}

	public void saveRecSession(RecSession recSession) {
		recSessionRepo.save(recSession);
		
	}
	
	
}
