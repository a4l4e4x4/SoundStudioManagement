/**
 * 
 */
package com.tfe.soundstudio.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author alex tolkmitt
 *
 */
@NodeEntity(label="SessionFile")
public class SessionFile {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@Index(unique=true)
	private String diskaddress;
	
	@Relationship(type="HAS_TRACK", direction=Relationship.OUTGOING)
	private List<Track> tracks = new ArrayList<>();

	@Relationship(type="FOR_A", direction=Relationship.OUTGOING)
	private List<Piece> pieces = new ArrayList<>();
	
	@Relationship(type="WORKS_ON", direction=Relationship.INCOMING)
	private List<RecSession> recsessions = new ArrayList<>();
	
	public SessionFile() {}

	public SessionFile(Long id, String name, String diskaddress, List<Track> tracks, List<Piece> pieces,
			List<RecSession> recsessions) {
		super();
		this.id = id;
		this.name = name;
		this.diskaddress = diskaddress;
		this.tracks = tracks;
		this.pieces = pieces;
		this.recsessions = recsessions;
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

	public String getDiskaddress() {
		return diskaddress;
	}

	public void setDiskaddress(String diskaddress) {
		this.diskaddress = diskaddress;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public List<RecSession> getRecsessions() {
		return recsessions;
	}

	public void setRecsessions(List<RecSession> recsessions) {
		this.recsessions = recsessions;
	}

	
	
}
