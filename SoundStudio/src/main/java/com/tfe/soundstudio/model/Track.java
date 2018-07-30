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

}
