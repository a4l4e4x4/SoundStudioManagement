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
			//System.out.print(project);
			//System.out.println(sc.next());
			//sc.nextLine();
			//sc.nextLine();
			//sc.nextLine();
			//sc.nextLine();
			//sc.findInLine("Track ");
			//sc.next("Track ");
			//System.out.println(sc.nextInt());
			
			while (sc.hasNext()) {
				
				//System.out.print(sc.nextLine() + "\n");
				if (sc.hasNext("Track")) {
					sc.next();
					System.out.println(sc.next());
					String line2 = sc.nextLine();
					String trackName = null;
					if (line2.startsWith("  [") && line2.endsWith("]")) {

						trackName = line2.substring(3, line2.length() - 1);
					}else {
						trackName = line2;
					}
					System.out.println(trackName);
				}
				sc.next();
				//System.out.println(sc.next());
				
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
