/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.TrackService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class ImportController {
	
	private static String UPLOADED_FOLDER = "/tmp/";

	private final InstrumentService instService;
	private final TrackService trackService;

	public ImportController(InstrumentService instService, TrackService trackService) {
		this.instService = instService;
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

		List<Track> tracks = new ArrayList<>();
		tracks = newImport.getTracks();
		/*
		 * for (Track onetrack: tracks){ //System.out.println(onetrack.getName());
		 * trackService.saveTrack(onetrack); for (TrackObject tobj :
		 * onetrack.getObjectList()) { System.out.println(tobj.getWave()); } }
		 */
		model.addAttribute("newtracks", tracks);
		} catch (IOException e) {
            e.printStackTrace();
        }

		return "import";
	}

}
