/**
 * 
 */
package com.tfe.soundstudio.repository;

import java.util.Set;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;


/**
 * @author alex tolkmitt
 *
 */

public interface InstrumentRepo extends Neo4jRepository<Instrument, Long>{
	
	Instrument findByInstName(@Param("instName") String instName);
	Set<Instrument> findByInstFamilyFamily(@Param("instFamily.family)") String instFamily);
	

}
