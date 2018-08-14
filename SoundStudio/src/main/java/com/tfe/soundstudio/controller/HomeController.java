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
    	Optional<InstFamily> instFamily = instService.readById(1L);
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
    	/*TrackObject tobj01 = new TrackObject();
    	tobj01.setName("new_object");
    	List<Track> trackList = new ArrayList<Track>();
    	Track track01 = trackService.findByName("new_track");
    	trackList.add(track01);
    	System.out.println(track01.getName());
    	tobj01.setTrackList(trackList);
    	trackService.saveTrackObject(tobj01); */
    	
    	return "home";
    }
    
    @RequestMapping(value="deleteTandTob")
    public String deleteTandTob() {
    	trackService.deleteAll();
    	
    	
    	return "home";
    }
    
    @RequestMapping(value="scanner")
    public String scanner() {
    	ReadFile newImport = new ReadFile();
    	newImport.ScanIt("/home/a4l4e4x4/TFE_ISFCE/TFE_Project_Spring/tracktest.txt");
    	
    	/*System.out.println(newImport.getTrackObjects().get(0).getName());
    	System.out.println(newImport.getTrackObjects().get(1).getName());
    	System.out.println(newImport.getTrackObjects().get(2).getName()); */
    	List<Track> tracks = new ArrayList<>();
    	tracks = newImport.getTracks();
    	for (Track onetrack: tracks){
    		//System.out.println(onetrack.getName());
    		for (TrackObject tobj : onetrack.getObjectList()) {
    			System.out.println(tobj.getStarttime());
    		}
    		
    	}
    	
    	return "index";
    }
    
    @RequestMapping(value="addAllInstruments")
    public String addAllInstruments() {
    	
    	List<Instrument> instruments = new ArrayList<>();
    	
    	//Instrument Families
    	
    	InstFamily woodwinds = new InstFamily();
    	woodwinds.setFamily("woodwinds");
    	
    	InstFamily strings = new InstFamily();
    	strings.setFamily("strings");
    	
    	InstFamily brass = new InstFamily();
    	brass.setFamily("brass");
    	
    	InstFamily percussion = new InstFamily();
    	percussion.setFamily("percussion");

    	InstFamily keyboards = new InstFamily();
    	keyboards.setFamily("keyboards");
    	
    	
    	//Woodwinds
    	
    	Instrument flute = new Instrument();
    	flute.setInstName("flute");
    	flute.setInstFamily(woodwinds);
    	instruments.add(flute);
    	
    	Instrument piccolo = new Instrument();
    	piccolo.setInstName("piccolo");
    	piccolo.setInstFamily(woodwinds);
    	instruments.add(piccolo);
    	
    	Instrument oboe = new Instrument();
    	oboe.setInstName("oboe");
    	oboe.setInstFamily(woodwinds);
    	instruments.add(oboe);
    	
    	Instrument bassoon = new Instrument();
    	bassoon.setInstName("bassoon");
    	bassoon.setInstFamily(woodwinds);
    	instruments.add(bassoon);
    	
    	Instrument clarinet = new Instrument();
    	clarinet.setInstName("clarinet");
    	clarinet.setInstFamily(woodwinds);
    	instruments.add(clarinet);

    	//Brass
    	
    	Instrument trumpet = new Instrument();
    	trumpet.setInstName("trumpet");
    	trumpet.setInstFamily(brass);
    	instruments.add(trumpet);
    	
    	Instrument french_horn = new Instrument();
    	french_horn.setInstName("french horn");
    	french_horn.setInstFamily(brass);
    	instruments.add(french_horn);
    	
    	Instrument trombone = new Instrument();
    	trombone.setInstName("trombone");
    	trombone.setInstFamily(brass);
    	instruments.add(trombone);
    	
    	Instrument tuba = new Instrument();
    	tuba.setInstName("tuba");
    	tuba.setInstFamily(brass);
    	instruments.add(tuba);
    	
    	//Strings
    	
    	Instrument violin = new Instrument();
    	violin.setInstName("violin");
    	violin.setInstFamily(strings);
    	instruments.add(violin);
    	
    	Instrument viola = new Instrument();
    	viola.setInstName("viola");
    	viola.setInstFamily(strings);
    	instruments.add(viola);
    	
    	Instrument cello = new Instrument();
    	cello.setInstName("cello");
    	cello.setInstFamily(strings);
    	instruments.add(cello);
    	
    	Instrument double_bass = new Instrument();
    	double_bass.setInstName("double bass");
    	double_bass.setInstFamily(strings);
    	instruments.add(double_bass);
    	
    	Instrument ac_guitar = new Instrument();
    	ac_guitar.setInstName("acoustic guitar");
    	ac_guitar.setInstFamily(strings);
    	instruments.add(ac_guitar);
    	
    	Instrument el_guitar = new Instrument();
    	el_guitar.setInstName("electric guitar");
    	el_guitar.setInstFamily(strings);
    	instruments.add(el_guitar);
    	
    	Instrument harp = new Instrument();
    	harp.setInstName("harp");
    	harp.setInstFamily(strings);
    	instruments.add(harp);
    	
    	
    	//Keyboards
    	
    	Instrument piano = new Instrument();
    	piano.setInstName("piano");
    	piano.setInstFamily(keyboards);
    	instruments.add(piano);
    	
    	Instrument celesta = new Instrument();
    	celesta.setInstName("piano");
    	celesta.setInstFamily(keyboards);
    	instruments.add(celesta);
    	
    	Instrument clavichord = new Instrument();
    	clavichord.setInstName("clavichord");
    	clavichord.setInstFamily(keyboards);
    	instruments.add(clavichord);
    	
    	Instrument accordion = new Instrument();
    	accordion.setInstName("accordion");
    	accordion.setInstFamily(keyboards);
    	instruments.add(accordion);
    	
    	//Percussion
    	
    	Instrument timpani = new Instrument();
    	timpani.setInstName("timpani");
    	timpani.setInstFamily(percussion);
    	instruments.add(timpani);
    	
    	Instrument cymbals = new Instrument();
    	cymbals.setInstName("cymbals");
    	cymbals.setInstFamily(percussion);
    	instruments.add(cymbals);
    	
    	Instrument snare = new Instrument();
    	snare.setInstName("snare");
    	snare.setInstFamily(percussion);
    	instruments.add(snare);
    	
    	Instrument xylophone = new Instrument();
    	xylophone.setInstName("xylophone");
    	xylophone.setInstFamily(percussion);
    	instruments.add(xylophone);
    	
    	Instrument drums = new Instrument();
    	drums.setInstName("drums");
    	drums.setInstFamily(percussion);
    	instruments.add(drums);
    	
    	
    	
    	Iterable<Instrument> instsToSave = instruments;
    	instService.saveInstruments(instsToSave);
    	
    	return "home";
    }

}
