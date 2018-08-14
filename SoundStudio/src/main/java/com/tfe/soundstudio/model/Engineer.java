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
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.OUTGOING)
	private List<Contact> contacts = new ArrayList<>();
	
	@Relationship(type="ENGINEERED_BY", direction=Relationship.INCOMING)
	private List<EngineerFee> engineerFee = new ArrayList<>();

	public Engineer() {}

	public Engineer(Long id, String name, String surname, List<Contact> contacts, List<EngineerFee> engineerFee) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.contacts = contacts;
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

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<EngineerFee> getEngineerFee() {
		return engineerFee;
	}

	public void setEngineerFee(List<EngineerFee> engineerFee) {
		this.engineerFee = engineerFee;
	};

	
	
}
