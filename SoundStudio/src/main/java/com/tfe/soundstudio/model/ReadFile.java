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
			sc.nextLine();
			sc.nextLine();
			sc.nextLine();
			sc.nextLine();
			sc.nextLine();
			sc.findInLine("Track ");
			System.out.println(sc.nextInt());
			
			/*while (sc.hasNext()) {
				
				System.out.print(sc.nextLine() + "\n");
			} */

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
