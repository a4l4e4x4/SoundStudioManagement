/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.Engineer;

/**
 * @author alex tolkmitt
 *
 */
public interface EngineerRepo extends Neo4jRepository<Engineer, Long> {
	

}
