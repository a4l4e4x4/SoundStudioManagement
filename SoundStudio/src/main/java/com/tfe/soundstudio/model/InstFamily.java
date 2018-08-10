/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="InstFamily")
public class InstFamily {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String family;
	
	@Relationship(type = "BELONGS_TO", direction=Relationship.INCOMING)
	private Set<Instrument> instruments = new HashSet<>();
	
	public InstFamily() {}

	public InstFamily(Long id, String family, Set<Instrument> instruments) {
		super();
		this.id = id;
		this.family = family;
		this.instruments = instruments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	};
	
	

	
}
