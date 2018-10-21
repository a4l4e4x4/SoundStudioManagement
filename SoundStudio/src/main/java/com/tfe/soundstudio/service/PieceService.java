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

	@Transactional
	public Iterable<Piece> findAll() {

		Iterable<Piece> result = pieceRepo.findAll(5);
		
		
		return result;
	}

	@Transactional
	public Piece findById(Long id) {
		Piece piece = pieceRepo.findById(id, 4).orElseThrow(()->new RuntimeException("No such Piece"));

		return piece;
	}

	@Transactional
	public void deleteById(Long id) {
		pieceRepo.deleteById(id);
		
	}

	public void saveAllPieces(Iterable<Piece> piecesToSave) {

		pieceRepo.saveAll(piecesToSave);
		
	}

}
