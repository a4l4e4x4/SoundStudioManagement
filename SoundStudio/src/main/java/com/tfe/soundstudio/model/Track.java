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
@NodeEntity (label="Track")
public class Track {
	
	@Id
	@GeneratedValue
	private Long id;
	Integer number;
	String name;
	
	@Relationship (type = "IS_IN", direction=Relationship.INCOMING)
	List<TrackObject> objectList;
	
	public Track() {}

	public Track(Long id, Integer number, String name, List<TrackObject> objectList) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.objectList = objectList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrackObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<TrackObject> objectList) {
		this.objectList = objectList;
	};
	
	

}
