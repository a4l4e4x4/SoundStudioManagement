/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="Musician")
public class Musician {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	
	@Relationship(type="HAS_CONTACT", direction=Relationship.OUTGOING)
	private Contact contact = new Contact();
	
	@Relationship(type="PLAYS_AN", direction=Relationship.OUTGOING)
	private Set<Instrument> instruments = new HashSet<>();
	
	@Relationship(type="RECORDED_BY", direction=Relationship.INCOMING)
	private List<TrackObject> trackObjects = new ArrayList<>();
	
	@Relationship(type="RECORDS", direction=Relationship.INCOMING)
	private List<MusicianFee> musicianFee = new ArrayList<>();
	
	public Musician() {}

	public Musician(Long id, String name, String surname, Contact contact, Set<Instrument> instruments,
			List<TrackObject> trackObjects, List<MusicianFee> musicianFee) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.contact = contact;
		this.instruments = instruments;
		this.trackObjects = trackObjects;
		this.musicianFee = musicianFee;
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

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public List<TrackObject> getTrackObjects() {
		return trackObjects;
	}

	public void setTrackObjects(List<TrackObject> trackObjects) {
		this.trackObjects = trackObjects;
	}

	public List<MusicianFee> getMusicianFee() {
		return musicianFee;
	}

	public void setMusicianFee(List<MusicianFee> musicianFee) {
		this.musicianFee = musicianFee;
	}

	


}
