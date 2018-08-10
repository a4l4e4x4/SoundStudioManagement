/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.service.TrackService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class ImportController {
	
	private static String UPLOADED_FOLDER = "/tmp/";

	
	private final TrackService trackService;

	public ImportController( TrackService trackService) {
		
		this.trackService = trackService;
	}
	
	@GetMapping(value="/uploadfile")
	public String uploadfile(Model model) {
		return "uploadfile";
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String scanner(@RequestParam(value="file") MultipartFile file, Model model) {
		
		try {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		 Files.write(path, bytes);

		// model.addAttribute("message", message);
		ReadFile newImport = new ReadFile();
		//newImport.ScanIt("/home/a4l4e4x4/TFE_ISFCE/TFE_Project_Spring/tracktest.txt");
		String filepath = path.toString();
		newImport.ScanIt(filepath);

		/*List<Track> tracks = new ArrayList<>();
		tracks = newImport.getTracks(); */
		Iterable<Track> tracks = newImport.getTracks();
		trackService.saveTracks(tracks);
		/*List<Track> tracks = newImport.getTracks();
		for (Track track : tracks) {
			Track newtrack = new Track();
			List<TrackObject> tobjToSave = new ArrayList<>();
			for (TrackObject tobj : track.getObjectList()) {
				
					tobjToSave.add(tobj);
					trackService.saveTrackObject(tobj);
					System.out.println(tobj.getStarttime());
			} 
			
			newtrack.setObjectList(tobjToSave);
			newtrack.setName(track.getName());
			newtrack.setNumber(track.getNumber());
			trackService.saveTrack(newtrack);
		} */
		
		//tracks.forEach(track -> track.getObjectList().forEach(tobj -> trackService.saveTrackObject(tobj)));
		
		model.addAttribute("newtracks", tracks);
		} catch (IOException e) {
            e.printStackTrace();
        }

		return "import";
	}

}
