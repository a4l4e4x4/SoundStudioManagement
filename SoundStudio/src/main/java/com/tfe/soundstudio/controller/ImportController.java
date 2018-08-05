/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.model.TrackObject;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.TrackService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class ImportController {

	private final InstrumentService instService;
	private final TrackService trackService;

	public ImportController(InstrumentService instService, TrackService trackService) {
		this.instService = instService;
		this.trackService = trackService;
	}

	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String scanner(Model model) {

		// model.addAttribute("message", message);
		ReadFile newImport = new ReadFile();
		newImport.ScanIt("/home/a4l4e4x4/TFE_ISFCE/TFE_Project_Spring/tracktest.txt");

		List<Track> tracks = new ArrayList<>();
		tracks = newImport.getTracks();
		/*
		 * for (Track onetrack: tracks){ //System.out.println(onetrack.getName());
		 * trackService.saveTrack(onetrack); for (TrackObject tobj :
		 * onetrack.getObjectList()) { System.out.println(tobj.getWave()); } }
		 */
		model.addAttribute("newtracks", tracks);

		return "import";
		//return "tables";
	}

}
