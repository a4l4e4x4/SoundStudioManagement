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
@NodeEntity (label="TrackObject")
public class TrackObject {
	
	@Id
	@GeneratedValue
	private Long id;
	String starttime;
	String name;
	String wave;
	
	@Relationship (type = "IS_IN")
	List<Track> trackList;

}
