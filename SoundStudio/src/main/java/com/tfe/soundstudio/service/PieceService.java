/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Piece;
import com.tfe.soundstudio.repository.PieceRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class PieceService {
	
	private final PieceRepo pieceRepo;

	public PieceService(PieceRepo pieceRepo) {
		super();
		this.pieceRepo = pieceRepo;
	}
	
	@Transactional
	public void savePiece(Piece piece) {
		pieceRepo.save(piece);
	}

}