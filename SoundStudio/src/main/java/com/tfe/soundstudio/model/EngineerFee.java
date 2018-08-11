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
@RelationshipEntity(type="ENGINEERED_BY")
public class EngineerFee {

	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	private RecSession recsession;
	@EndNode
	private Engineer engineer;
	
	private Double fee;
	
	public EngineerFee() {}

	public EngineerFee(Long id, RecSession recsession, Engineer engineer, Double fee) {
		super();
		this.id = id;
		this.recsession = recsession;
		this.engineer = engineer;
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

	public Engineer getEngineer() {
		return engineer;
	}

	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	};
	
	
}
