/**
 * 
 */
package com.tfe.soundstudio.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author alex tolkmitt
 *
 */
@RelationshipEntity(type="RECORDS")
public class MusicianFee {
	
	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	private RecSession recsession;
	@EndNode
	private Musician musician;
	
	private Double fee;
	
	public MusicianFee() {}

	public MusicianFee(Long id, RecSession recsession, Musician musician, Double fee) {
		super();
		this.id = id;
		this.recsession = recsession;
		this.musician = musician;
		this.fee = fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RecSession getRecsession() {
		return recsession;
	}

	public void setRecsession(RecSession recsession) {
		this.recsession = recsession;
	}

	public Musician getMusician() {
		return musician;
	}

	public void setMusician(Musician musician) {
		this.musician = musician;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	};
	
	

}
