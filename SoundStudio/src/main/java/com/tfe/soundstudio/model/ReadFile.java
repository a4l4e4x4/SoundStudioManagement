/**
 * 
 */
package com.tfe.soundstudio.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author alex tolkmitt
 *
 */
public class ReadFile {
	public static void ScanIt(String fileName) {
		try {
			File file = new File(fileName);
			
			Scanner sc = new Scanner(file);
			
			
			sc.nextLine();
			sc.findInLine("Project: ");
			String line = sc.nextLine();
			String project = null;
			if (line.startsWith("\"") && line.endsWith("\"")) {

			    project = line.substring(1, line.length() - 1);
			}else {
				project = line;
			}
			//String project = line.replaceAll("\"", "");
			System.out.print(project + "\n");
			sc.nextLine(); //empty line
			sc.nextLine(); //line with *****
			sc.nextLine(); //line with Start Time Object Name Wave Project
			sc.nextLine(); //line with *****
			
			 while (sc.hasNext()) {
				
				//System.out.print(sc.nextLine() + "\n");
				if (sc.hasNext("Track")) {
					sc.next(); //space
					Integer trackNumber = sc.nextInt();
					System.out.println(trackNumber);
					String line2 = sc.nextLine();
					String trackName = null;
					if (line2.startsWith("  [") && line2.endsWith("]")) {

						trackName = line2.substring(3, line2.length() - 1);
					}else {
						trackName = line2;
					}
					System.out.println(trackName);
					sc.nextLine(); //line with ******
						
						String line3 = sc.nextLine();
						//String next = file.next("[\\S ]+");
						String[] lineSplit= line3.split("\\t");
						String objectPosition = lineSplit[0];
						System.out.println(objectPosition);
						String objectName = lineSplit[1];
						System.out.println(objectName);
						if (!(lineSplit[2].isEmpty())) {
						String objectAddress = lineSplit[2].substring(1, lineSplit[2].length() -1);
						System.out.println(objectAddress);
						}else {
							String objectAddress = lineSplit[3].substring(1, lineSplit[3].length() -1);
							System.out.println(objectAddress);
						}
						
				}

				//System.out.println(sc.nextLine());
				//sc.nextLine();
				//System.out.println(sc.next());
				String line4 = sc.nextLine();
				if (!(line4.isEmpty()) && !(line4.startsWith("*"))) {
					//System.out.println(line4);
					String[] lineSplit2= line4.split("\\t");
					String objectPosition = lineSplit2[0];
					System.out.println(objectPosition);
					String objectName = lineSplit2[1];
					System.out.println(objectName);
					if (!(lineSplit2[2].isEmpty())) {
					String objectAddress = lineSplit2[2].substring(1, lineSplit2[2].length() -1);
					System.out.println(objectAddress);
					}else {
						String objectAddress = lineSplit2[3].substring(1, lineSplit2[3].length() -1);
						System.out.println(objectAddress);
					}
				}
				
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
