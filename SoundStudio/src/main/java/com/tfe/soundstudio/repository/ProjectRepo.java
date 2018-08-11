/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tfe.soundstudio.model.Project;

/**
 * @author alex tolkmitt
 *
 */
public interface ProjectRepo extends Neo4jRepository<Project, Long> {

}
