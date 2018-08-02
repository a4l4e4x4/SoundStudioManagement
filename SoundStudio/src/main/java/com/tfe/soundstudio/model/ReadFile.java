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

	private String projectName;
	private List<Track> tracks;
	private List<TrackObject> trackObjects;
	private Track newTrack;
	private TrackObject newTrackObject;
	
	public ReadFile () {};

	public ReadFile(String projectName, List<Track> tracks, List<TrackObject> trackObjects, Track newTrack,
			TrackObject newTrackObject) {
		super();
		this.projectName = projectName;
		this.tracks = tracks;
		this.trackObjects = trackObjects;
		this.newTrack = newTrack;
		this.newTrackObject = newTrackObject;
	}



	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public void ScanIt(String fileName) {
		try {
			File file = new File(fileName);

			Scanner sc = new Scanner(file);
			
			tracks = new ArrayList<>();
			trackObjects = new ArrayList<>();

			sc.nextLine();
			sc.findInLine("Project: ");
			String line = sc.nextLine();
			projectName = null;
			if (line.startsWith("\"") && line.endsWith("\"")) {

				projectName = line.substring(1, line.length() - 1);
			} else {
				projectName = line;
			}
			// String project = line.replaceAll("\"", "");
			System.out.print(projectName + "\n");
			sc.nextLine(); // empty line
			sc.nextLine(); // line with *****
			sc.nextLine(); // line with Start Time Object Name Wave Project
			sc.nextLine(); // line with *****

			while (sc.hasNext()) {

				// System.out.print(sc.nextLine() + "\n");
				
				
				
				if (sc.hasNext("Track")) {
					sc.next(); // space
					if (newTrack != null) {
					newTrack.setObjectList(trackObjects);
					tracks.add(newTrack);
					}
					newTrack = new Track();
					newTrackObject = new TrackObject();
					trackObjects = new ArrayList<>();
					
					
					Integer trackNumber = sc.nextInt();
					newTrack.setNumber(trackNumber);
					System.out.println(trackNumber);
					String line2 = sc.nextLine();
					String trackName = null;
					if (line2.startsWith("  [") && line2.endsWith("]")) {

						trackName = line2.substring(3, line2.length() - 1);
						newTrack.setName(trackName);
					} else {
						trackName = line2;
						newTrack.setName(trackName);
					}
					System.out.println(trackName);
					sc.nextLine(); // line with ******

					String line3 = sc.nextLine();
					// String next = file.next("[\\S ]+");
					String[] lineSplit = line3.split("\\t");
					String objectPosition = lineSplit[0];

					newTrackObject.setStarttime(objectPosition);
					System.out.println(objectPosition);

					String objectName = lineSplit[1];
					newTrackObject.setName(objectName);
					System.out.println(objectName);
					if (!(lineSplit[2].isEmpty())) {
						String objectAddress = lineSplit[2].substring(1, lineSplit[2].length() - 1);
						newTrackObject.setWave(objectAddress);
						System.out.println(objectAddress);
					} else {
						String objectAddress = lineSplit[3].substring(1, lineSplit[3].length() - 1);
						newTrackObject.setWave(objectAddress);
						System.out.println(objectAddress);
					}
					trackObjects.add(newTrackObject);
				}

				String line4 = sc.nextLine();
				if (!(line4.isEmpty()) && !(line4.startsWith("*"))) {
					// System.out.println(line4);
					
					String[] lineSplit2 = line4.split("\\t");
					String objectPosition2 = lineSplit2[0];
					newTrackObject.setStarttime(objectPosition2);
					System.out.println(objectPosition2);
					String objectName2 = lineSplit2[1];
					newTrackObject.setName(objectName2);
					System.out.println(objectName2);
					if (!(lineSplit2[2].isEmpty())) {
						String objectAddress2 = lineSplit2[2].substring(1, lineSplit2[2].length() - 1);
						newTrackObject.setWave(objectAddress2);
						System.out.println(objectAddress2);
					} else {
						String objectAddress2 = lineSplit2[3].substring(1, lineSplit2[3].length() - 1);
						newTrackObject.setWave(objectAddress2);
						System.out.println(objectAddress2);
					}
					trackObjects.add(newTrackObject);
				}
			} 

			sc.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
