/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="RecSession")
public class RecSession {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Integer duration;
	
	@DateString("dd-MM-yy")
    private Date sessionDate;
	
	@Relationship(type="WORKS_ON", direction=Relationship.OUTGOING)
	private List<SessionFile> sessionFiles = new ArrayList<>();
	
	@Relationship(type="RECORDS", direction=Relationship.OUTGOING)
	private List<MusicianFee> musiciansFees = new ArrayList<>();

	@Relationship(type="ENGINEERED_BY", direction=Relationship.OUTGOING)
	private List<EngineerFee> engineerFees = new ArrayList<>();
	
	public RecSession() {}

	public RecSession(Long id, Integer duration, Date sessionDate, List<SessionFile> sessionFiles,
			List<MusicianFee> musiciansFees, List<EngineerFee> engineerFees) {
		super();
		this.id = id;
		this.duration = duration;
		this.sessionDate = sessionDate;
		this.sessionFiles = sessionFiles;
		this.musiciansFees = musiciansFees;
		this.engineerFees = engineerFees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public List<SessionFile> getSessionFiles() {
		return sessionFiles;
	}

	public void setSessionFiles(List<SessionFile> sessionFiles) {
		this.sessionFiles = sessionFiles;
	}

	public List<MusicianFee> getMusiciansFees() {
		return musiciansFees;
	}

	public void setMusiciansFees(List<MusicianFee> musiciansFees) {
		this.musiciansFees = musiciansFees;
	}

	public List<EngineerFee> getEngineerFees() {
		return engineerFees;
	}

	public void setEngineerFees(List<EngineerFee> engineerFees) {
		this.engineerFees = engineerFees;
	};
	
	
}
