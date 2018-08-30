/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.tfe.soundstudio.model.Musician;

/**
 * @author alex tolkmitt
 *
 */
public interface MusicianRepo extends Neo4jRepository<Musician, Long> {

	Musician getByName(@Param("name")String name);

	Long deleteByName(@Param("name")String name);

}
