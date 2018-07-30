/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;
import com.tfe.soundstudio.service.InstrumentService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class HomeController {
	
	
	private final InstrumentService instService;
	public HomeController(InstrumentService instService) {
		this.instService = instService;
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

}
