/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.tfe.soundstudio.model.Track;

/**
 * @author alex tolkmitt
 *
 */
public interface TrackRepo extends Neo4jRepository<Track, Long> {
	
	Track findByNumber(@Param("number") Integer number);
	Track findByName(@Param("name")String name);

}
