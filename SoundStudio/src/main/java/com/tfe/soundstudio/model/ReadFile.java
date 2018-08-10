/**
 * 
 */
package com.tfe.soundstudio.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author alex tolkmitt
 *
 */
public class ReadFile {

	private String sessionfile_address;
	private SessionFile sessionFile = new SessionFile();
	private List<Track> tracks;
	private List<TrackObject> trackObjects;
	private Track newTrack;
	private TrackObject newTrackObject;
	private TrackObject newTrackObject2;
	
	
	public ReadFile () {};


	public ReadFile(String sessionfile_address, SessionFile sessionFile, List<Track> tracks,
			List<TrackObject> trackObjects, Track newTrack, TrackObject newTrackObject, TrackObject newTrackObject2) {
		super();
		this.sessionfile_address = sessionfile_address;
		this.sessionFile = sessionFile;
		this.tracks = tracks;
		this.trackObjects = trackObjects;
		this.newTrack = newTrack;
		this.newTrackObject = newTrackObject;
		this.newTrackObject2 = newTrackObject2;
	}



	public String getSessionfile_address() {
		return sessionfile_address;
	}


	public void setSessionfile_address(String sessionfile_address) {
		this.sessionfile_address = sessionfile_address;
	}


	public SessionFile getSessionFile() {
		return sessionFile;
	}


	public void setSessionFile(SessionFile sessionFile) {
		this.sessionFile = sessionFile;
	}


	public List<Track> getTracks() {
		return tracks;
	}


	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}


	public List<TrackObject> getTrackObjects() {
		return trackObjects;
	}


	public void setTrackObjects(List<TrackObject> trackObjects) {
		this.trackObjects = trackObjects;
	}


	public Track getNewTrack() {
		return newTrack;
	}


	public void setNewTrack(Track newTrack) {
		this.newTrack = newTrack;
	}


	public TrackObject getNewTrackObject() {
		return newTrackObject;
	}


	public void setNewTrackObject(TrackObject newTrackObject) {
		this.newTrackObject = newTrackObject;
	}


	public TrackObject getNewTrackObject2() {
		return newTrackObject2;
	}


	public void setNewTrackObject2(TrackObject newTrackObject2) {
		this.newTrackObject2 = newTrackObject2;
	}


	/**
	 * Scan standard txt file from Samplitude and save Tracks and Track Objects.
	 * @param fileName
	 */
	public void ScanIt(String fileName) {
		try {
			File file = new File(fileName);

			Scanner sc = new Scanner(file);
			//new list of tracks and track objects
			tracks = new ArrayList<>();
			trackObjects = new ArrayList<>();

			sc.nextLine(); //skip line that says "Samplitude - Object List
			sc.findInLine("Project: "); //find project unique name with address
			String line = sc.nextLine();
			sessionfile_address = null;
			/*
			 * remove quotes from project address if inside quotes
			 */
			if (line.startsWith("\"") && line.endsWith("\"")) {

				sessionfile_address = line.substring(1, line.length() - 1);
			} else {
				sessionfile_address = line;
			}
			sessionFile.setDiskaddress(sessionfile_address);
			// console print project session file
			System.out.print(sessionfile_address + "\n");
			
			sc.nextLine(); // empty line after project address
			sc.nextLine(); // line with *****
			sc.nextLine(); // line with Start Time Object Name Wave Project
			sc.nextLine(); // line with *****
			
			//scan track list with their objects
			while (sc.hasNext()) {
				
				if (sc.hasNext("Track")) { //if it is a new track
					sc.next(); // space between "Track" and its number
					
					/*
					 * if it is a new track, save previous track with its object list
					 * and prepare new track, new track-object and new object list
					 */
					if (newTrack != null) {
					newTrack.setObjectList(trackObjects);
					tracks.add(newTrack);
					}
					newTrack = new Track();
					newTrackObject = new TrackObject();
					trackObjects = new ArrayList<>();
					
					//populate track object
					
					//track number
					Integer trackNumber = sc.nextInt();
					newTrack.setNumber(trackNumber);
					//System.out.println(trackNumber);
					
					//Session File for Track
					
					
					newTrack.setSessionFile(sessionFile);
					
					/*
					 * track names are inside []
					 * sometimes with empty spaces before
					 * remove [] and empty space if they exist
					 */
					String line2 = sc.nextLine();
					String trackName = null;
					if (line2.startsWith("  [") && line2.endsWith("]")) {

						trackName = line2.substring(3, line2.length() - 1);
						newTrack.setName(trackName);
					} else {
						trackName = line2;
						newTrack.setName(trackName);
					}
					//System.out.println(trackName);
					sc.nextLine(); // line with ******
					
					/*
					 * next line has object position, object name and object address
					 * split line and save each element
					 */
					String line3 = sc.nextLine();
					// String next = file.next("[\\S ]+");
					String[] lineSplit = line3.split("\\t");
					String objectPosition = lineSplit[0];

					newTrackObject.setStarttime(objectPosition);
					//System.out.println(objectPosition);

					String objectName = lineSplit[1];
					newTrackObject.setName(objectName);
					//System.out.println(objectName);
					
					/*
					 * sometimes there is a LF (line feed) in line
					 * if so, remove it to read the object address
					 * remove also quotes ("") from object address
					 */
					if (!(lineSplit[2].isEmpty())) {
						String objectAddress = lineSplit[2].substring(1, lineSplit[2].length() - 1);
						newTrackObject.setWave(objectAddress);
						//System.out.println(objectAddress);
					} else {
						String objectAddress = lineSplit[3].substring(1, lineSplit[3].length() - 1);
						newTrackObject.setWave(objectAddress);
						//System.out.println(objectAddress);
					}
					//add first object from track to track object list
					/*if (newTrack != null && newTrackObject != null) {
					newTrackObject.getTrackList().add(newTrack);
					} */
					trackObjects.add(newTrackObject);
					
				}
				
				/*
				 * if line is not empty or has **** as delimiter
				 * than track has more objects
				 */
				String line4 = sc.nextLine();
				if (!(line4.isEmpty()) && !(line4.startsWith("*"))) {
					// System.out.println(line4);
					
					/*
					 * next line has object position, object name and object address
					 * split line and save each element as above
					 */
					newTrackObject2 = new TrackObject();
					String[] lineSplit2 = line4.split("\\t");
					String objectPosition2 = lineSplit2[0];
					newTrackObject2.setStarttime(objectPosition2);
					//System.out.println(objectPosition2);
					String objectName2 = lineSplit2[1];
					newTrackObject2.setName(objectName2);
					//System.out.println(objectName2);
					if (!(lineSplit2[2].isEmpty())) {
						String objectAddress2 = lineSplit2[2].substring(1, lineSplit2[2].length() - 1);
						newTrackObject2.setWave(objectAddress2);
						//System.out.println(objectAddress2);
					} else {
						String objectAddress2 = lineSplit2[3].substring(1, lineSplit2[3].length() - 1);
						newTrackObject2.setWave(objectAddress2);
						//System.out.println(objectAddress2);
					}
					//save subsequent track object to track object list
					//newTrackObject.getTrackList().add(newTrack);
					trackObjects.add(newTrackObject2);
				}
			}
			//close scan
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
