/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;
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
public class HomeController {
	
	
	private final InstrumentService instService;
	private final TrackService trackService;
	public HomeController(InstrumentService instService, TrackService trackService) {
		this.instService = instService;
		this.trackService = trackService;
	}
	
	// Inject via application.properties
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
 
        return "home";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.GET)
    public String save() {
    	Instrument instrument = new Instrument();
    	instrument.setInstName("flute");
    	InstFamily instFamily = new InstFamily();
    	instFamily.setFamily("woodwinds");
    	instrument.setInstFamily(instFamily);
    	
    	
    	/*Set<Instrument> instruments = new HashSet<Instrument>();
    	instruments.add(instrument);
    	
    	InstFamily instFamily = new InstFamily();
    	instFamily.setFamily("woodwinds");
    	instFamily.setInstruments(instruments); */
    	
    	instService.saveInstrument(instrument);
    	
    	
    	
    	return "home";
    }
    
    @RequestMapping(value="/read", method = RequestMethod.GET)
    public String read() {
    	Optional<InstFamily> instFamily = instService.readByID(1L);
    	System.out.println(instFamily.get().getFamily());
    	return "home";
    }
    
    @RequestMapping(value="/deleteInstruments")
    public String deleteInstruments() {
    	instService.deleteAllInstruments();
    	
    	return "home";
    }
    
    @RequestMapping(value="/deleteInstFamily")
    public String deleteInstFamily() {
    	instService.deleteAllInstFamily();
    	
    	return "home";
    }
    
    @RequestMapping(value="addTracks")
    public String addTracks() {
    	Track track01 = new Track();
    	track01.setNumber(1);
    	track01.setName("new_track");
    	
    	trackService.saveTrack(track01);
    	
    	//System.out.println(track01.getName());
    	
    	return "home";
    }
    
    @RequestMapping(value="addTrackObjects")
    public String addTrackObjects() {
    	TrackObject tobj01 = new TrackObject();
    	tobj01.setName("new_object");
    	List<Track> trackList = new ArrayList<Track>();
    	Track track01 = trackService.findByName("new_track");
    	trackList.add(track01);
    	System.out.println(track01.getName());
    	tobj01.setTrackList(trackList);
    	trackService.saveTrackObject(tobj01); 
    	
    	return "home";
    }
    
    @RequestMapping(value="deleteTandTob")
    public String deleteTandTob() {
    	trackService.deleteAll();
    	
    	
    	return "home";
    }
    
    @RequestMapping(value="scanner")
    public String scanner() {
    	ReadFile.ScanIt("/home/a4l4e4x4/TFE_ISFCE/TFE_Project_Spring/tracktest.txt");
    	
    	return "home";
    }

}
