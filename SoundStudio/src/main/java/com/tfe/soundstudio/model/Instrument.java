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
@NodeEntity (label="Instrument")
public class Instrument {
	
	@Id
	@GeneratedValue
	private Long id;
	private String instName;
	
	@Relationship(type = "BELONGS_TO", direction=Relationship.OUTGOING)
	private InstFamily instFamily;
	
	@Relationship(type="PLAYS_AN", direction=Relationship.INCOMING)
	private Set<Musician> musicians = new HashSet<>();
	
	public Instrument() {}

	public Instrument(Long id, String instName, InstFamily instFamily, Set<Musician> musicians) {
		super();
		this.id = id;
		this.instName = instName;
		this.instFamily = instFamily;
		this.musicians = musicians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public InstFamily getInstFamily() {
		return instFamily;
	}

	public void setInstFamily(InstFamily instFamily) {
		this.instFamily = instFamily;
	}

	public Set<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(Set<Musician> musicians) {
		this.musicians = musicians;
	};
	
	

}
