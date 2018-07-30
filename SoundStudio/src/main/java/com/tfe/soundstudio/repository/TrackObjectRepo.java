/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.tfe.soundstudio.model.TrackObject;

/**
 * @author alex tolkmitt
 *
 */
public interface TrackObjectRepo extends Neo4jRepository<TrackObject, Long> {
	
	TrackObject findByWave(@Param("wave") String wave);

}
