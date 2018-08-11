/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.RecSession;

/**
 * @author alex tolkmitt
 *
 */
public interface RecSessionRepo extends Neo4jRepository<RecSession, Long> {

}
