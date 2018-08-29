/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.TrackObjectFile;

/**
 * @author alex tolkmitt
 *
 */
public interface TrackObjectFileRepo extends Neo4jRepository<TrackObjectFile, Long> {

}
