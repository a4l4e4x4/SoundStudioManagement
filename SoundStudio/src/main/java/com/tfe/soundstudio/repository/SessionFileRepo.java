/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.SessionFile;

/**
 * @author alex tolkmitt
 *
 */
public interface SessionFileRepo extends Neo4jRepository<SessionFile, Long> {

}
