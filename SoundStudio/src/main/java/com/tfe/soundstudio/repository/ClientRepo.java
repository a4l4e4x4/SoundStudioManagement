/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.Client;

/**
 * @author alex tolkmitt
 *
 */
public interface ClientRepo extends Neo4jRepository<Client, Long> {

}
