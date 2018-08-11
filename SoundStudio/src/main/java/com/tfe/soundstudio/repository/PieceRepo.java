/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.Piece;

/**
 * @author alex tolkmitt
 *
 */
public interface PieceRepo extends Neo4jRepository<Piece, Long> {

}
