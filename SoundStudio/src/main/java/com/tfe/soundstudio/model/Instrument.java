/**
 * 
 */
package com.tfe.soundstudio.model;

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
	@Relationship(type = "BELONGS_TO")
	private InstFamily instFamily;
	
	public Instrument() {};
	
	public Instrument(Long id, String instName, InstFamily instFamily) {
		super();
		this.id = id;
		this.instName = instName;
		this.instFamily = instFamily;
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
	
	
	

}
