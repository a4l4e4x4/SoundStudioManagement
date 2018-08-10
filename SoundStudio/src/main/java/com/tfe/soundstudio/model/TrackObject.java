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
@NodeEntity (label="TrackObject")
public class TrackObject {
	
	@Id
	@GeneratedValue
	private Long id;
	private String starttime;
	private String name;
	private String wave;
	
	@Relationship (type = "IS_IN", direction=Relationship.OUTGOING)
	private List<Track> trackList = new ArrayList<>();
	
	@Relationship (type= "RECORDED_BY", direction=Relationship.OUTGOING)
	private List<Musician> musicians = new ArrayList<>();
	
	public TrackObject() {}

	public TrackObject(Long id, String starttime, String name, String wave, List<Track> trackList,
			List<Musician> musicians) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.name = name;
		this.wave = wave;
		this.trackList = trackList;
		this.musicians = musicians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
	}

	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	};


}
