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
@NodeEntity(label="TrackObjectFile")
public class TrackObjectFile {
	
	@Id
	@GeneratedValue
	private Long id;
	private String fileLocation;
	
	@Relationship (type= "RECORDED_BY", direction=Relationship.OUTGOING)
	private List<Musician> musicians = new ArrayList<>();
	
	@Relationship(type="HAS_FILE", direction=Relationship.INCOMING)
	private List<TrackObject> trackObject = new ArrayList<>();
	
	public TrackObjectFile() {}

	public TrackObjectFile(Long id, String fileLocation, List<Musician> musicians, List<TrackObject> trackObject) {
		super();
		this.id = id;
		this.fileLocation = fileLocation;
		this.musicians = musicians;
		this.trackObject = trackObject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	}

	public List<TrackObject> getTrackObject() {
		return trackObject;
	}

	public void setTrackObject(List<TrackObject> trackObject) {
		this.trackObject = trackObject;
	}
	
	
	
	

}
