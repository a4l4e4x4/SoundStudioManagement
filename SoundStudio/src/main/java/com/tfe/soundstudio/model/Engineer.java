/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="Engineer")
public class Engineer {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	private Contact contact;
	
	@Relationship(type="ENGINEERED_BY", direction=Relationship.INCOMING)
	private List<EngineerFee> engineerFee = new ArrayList<>();

	public Engineer() {}

	public Engineer(Long id, String name, String surname, Contact contact, List<EngineerFee> engineerFee) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.contact = contact;
		this.engineerFee = engineerFee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<EngineerFee> getEngineerFee() {
		return engineerFee;
	}

	public void setEngineerFee(List<EngineerFee> engineerFee) {
		this.engineerFee = engineerFee;
	};
	
	
}
