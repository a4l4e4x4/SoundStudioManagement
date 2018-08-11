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
@NodeEntity(label="Project")
public class Project {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@Relationship(type="HAS_A")
	private Client client;
	
	@Relationship(type="FROM_PROJECT")
	private List<Piece> pieces = new ArrayList<>();
	
	public Project() {}

	public Project(Long id, String name, Client client, List<Piece> pieces) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
		this.pieces = pieces;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	};
	
	

}
