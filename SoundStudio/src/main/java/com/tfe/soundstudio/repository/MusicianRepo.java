/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.Musician;

/**
 * @author alex tolkmitt
 *
 */
public interface MusicianRepo extends Neo4jRepository<Musician, Long> {

}
