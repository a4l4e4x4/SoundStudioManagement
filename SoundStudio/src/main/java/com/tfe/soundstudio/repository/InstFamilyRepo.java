/**
 * 
 */
package com.tfe.soundstudio.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.tfe.soundstudio.model.InstFamily;

/**
 * @author alex tolkmitt
 *
 */
public interface InstFamilyRepo extends Neo4jRepository<InstFamily, Long> {
	
	InstFamily findByFamily(@Param("family") String family);

}
