/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="Client")
public class Client {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String address;
	private List<Contact> contacts;
	
	@Relationship(type="HAS_A", direction=Relationship.OUTGOING)
	private List<Project> projects;
	
	public Client() {}

	public Client(Long id, String name, String address, List<Contact> contacts, List<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contacts = contacts;
		this.projects = projects;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	};
	
	

}
