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
@NodeEntity(label="Piece")
public class Piece {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String composer;
	
	@Relationship(type="FOR_A")
	private List<SessionFile> sessionFiles = new ArrayList<>();
	
	@Relationship(type="FROM_PROJECT", direction=Relationship.OUTGOING)
	private List<Project> projects = new ArrayList<>();
	
	public Piece() {}

	public Piece(Long id, String name, String composer, List<SessionFile> sessionFiles, List<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.composer = composer;
		this.sessionFiles = sessionFiles;
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

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public List<SessionFile> getSessionFiles() {
		return sessionFiles;
	}

	public void setSessionFiles(List<SessionFile> sessionFiles) {
		this.sessionFiles = sessionFiles;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	
}
