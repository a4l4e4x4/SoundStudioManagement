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
@NodeEntity(label="Client")

public class Client {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name;
	private String description;
	private String website;
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.OUTGOING)
	private Contact contact = new Contact();
	
	@Relationship(type="HAS_A", direction=Relationship.OUTGOING)
	private List<Project> projects = new ArrayList<>();
	
	public Client() {}

	public Client(Long id, String name, String description, String website, Contact contact, List<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.website = website;
		this.contact = contact;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	

}
