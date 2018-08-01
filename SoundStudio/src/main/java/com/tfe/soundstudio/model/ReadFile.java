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
	private List<Track> tracks = new ArrayList<>();
	private List<TrackObject> trackObjects = new ArrayList<>();
	
	public ReadFile () {};
	
	

	public ReadFile(String projectName, List<Track> tracks, List<TrackObject> trackObjects) {
		super();
		this.projectName = projectName;
		this.tracks = tracks;
		this.trackObjects = trackObjects;
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



	public void ScanIt(String fileName) {
		try {
			File file = new File(fileName);

			Scanner sc = new Scanner(file);

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
				
				Track newTrack = new Track();
				
				if (sc.hasNext("Track")) {
					sc.next(); // space
					
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
					TrackObject newTrackObject = new TrackObject();
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
					if (newTrackObject != null) {
						trackObjects.add(newTrackObject);
					//	System.out.println(newTrackObject.getName());
					}
					//tracks.add(newTrack);

				}

				// System.out.println(sc.nextLine());
				// sc.nextLine();
				// System.out.println(sc.next());
				String line4 = sc.nextLine();
				if (!(line4.isEmpty()) && !(line4.startsWith("*"))) {
					// System.out.println(line4);
					
					TrackObject newTrackObject = new TrackObject();
					String[] lineSplit2 = line4.split("\\t");
					String objectPosition = lineSplit2[0];
					newTrackObject.setStarttime(objectPosition);
					System.out.println(objectPosition);
					String objectName = lineSplit2[1];
					newTrackObject.setName(objectName);
					System.out.println(objectName);
					if (!(lineSplit2[2].isEmpty())) {
						String objectAddress = lineSplit2[2].substring(1, lineSplit2[2].length() - 1);
						newTrackObject.setWave(objectAddress);
						System.out.println(objectAddress);
					} else {
						String objectAddress = lineSplit2[3].substring(1, lineSplit2[3].length() - 1);
						newTrackObject.setWave(objectAddress);
						System.out.println(objectAddress);
					}
					trackObjects.add(newTrackObject);
				}
				newTrack.setObjectList(trackObjects);
				tracks.add(newTrack);
				//System.out.println(tracks.get(0).name);
			}

			sc.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
