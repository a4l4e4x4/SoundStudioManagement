/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Contact;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;
import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.model.Piece;
import com.tfe.soundstudio.model.Project;
import com.tfe.soundstudio.service.ClientService;
import com.tfe.soundstudio.service.EngineerService;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.MusicianService;
import com.tfe.soundstudio.service.PieceService;
import com.tfe.soundstudio.service.ProjectService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class AdditionsController {
	
	private final ClientService clientService;
	private final EngineerService engineerService;
	private final MusicianService musicianService;
	private final InstrumentService instrumentService;
	private final ProjectService projectService;
	private final PieceService pieceService;
	public AdditionsController(ClientService clientService, EngineerService engineerService,
			MusicianService musicianService, InstrumentService instrumentService, ProjectService projectService,
			PieceService pieceService) {
		super();
		this.clientService = clientService;
		this.engineerService = engineerService;
		this.musicianService = musicianService;
		this.instrumentService = instrumentService;
		this.projectService = projectService;
		this.pieceService = pieceService;
	}
	
	
	@GetMapping(value="add/addClient")
	public String addClientGet(Model model) {
		
		model.addAttribute("client", new Client());
		model.addAttribute("contact", new Contact());
		
		return "add/addClient";
	}
	
	@PostMapping(value="add/addClient")
	public String addClientPost(@Valid Client client, BindingResult result, @Valid Contact contact, BindingResult result2, Model model) {
		
		client.getContacts().add(contact);
		clientService.saveClient(client);
		return "redirect:/lists/clientList";
	}
	
	@GetMapping(value="add/addMusician")
	public String addMusicianGet(Model model) {
		//Iterable<Instrument> instruments =instrumentService.findAllInstruments();
		Set<Instrument> woodwinds = instrumentService.findByInstFamilyFamily("woodwinds");
		Set<Instrument> strings = instrumentService.findByInstFamilyFamily("strings");
		Set<Instrument> brass = instrumentService.findByInstFamilyFamily("brass");
		Set<Instrument> keyboards = instrumentService.findByInstFamilyFamily("keyboards");
		Set<Instrument> percussions = instrumentService.findByInstFamilyFamily("percussion");
		
		Set<Long> instID = new HashSet<>();
		model.addAttribute("instID", instID);
		//model.addAttribute("instruments", instruments);
		model.addAttribute("woodwinds", woodwinds);
		model.addAttribute("strings", strings);
		model.addAttribute("brass", brass);
		model.addAttribute("keyboards", keyboards);
		model.addAttribute("percussions", percussions);
		model.addAttribute("musician", new Musician());
		model.addAttribute("contact", new Contact());
		
		return "add/addMusician";
	}
	
	@PostMapping(value="add/addMusician")
	public String addMusicianPost(@Valid Musician musician, BindingResult result, @Valid Contact contact, BindingResult result2, @RequestParam(value="instID", required=false) HashSet<String> instIDs, Model model) {
		
		if(instIDs != null) {
		for(String id : instIDs) {
			Long realID = Long.parseLong(id);
			Instrument inst = instrumentService.findById(realID).orElseThrow(() -> new RuntimeException("No instrument!"));
			
			musician.getInstruments().add(inst);
			//System.out.println(id);
		}
			
		}
		musician.getContacts().add(contact);
		musicianService.saveMusician(musician);
		
		return "redirect:/lists/musicianList";
	}
	
	@GetMapping(value="add/addEngineer")
	public String addEngineerGet(Model model) {
		
		model.addAttribute("engineer", new Engineer());
		model.addAttribute("contact", new Contact());
		
		return "add/addEngineer";
	}
	
	@PostMapping(value="add/addEngineer")
	public String addEngineerPost(@Valid Engineer engineer, BindingResult result, @Valid Contact contact, BindingResult result2, Model model) {
		engineer.getContacts().add(contact);
		engineerService.saveEngineer(engineer);
		return "redirect:/lists/engineerList";
	}
	
	@GetMapping(value="add/addProject")
	public String addProjectGet(Model model) {
		Iterable<Client> clients = clientService.findAll();
		
		model.addAttribute("project", new Project());
		model.addAttribute("clients", clients);
		
		return "add/addProject";
	}
	
	@PostMapping(value="add/addProject")
	public String addProjectPost(@Valid Project project, BindingResult result, @RequestParam("clientID") String clientID, Model model) {
		if (result.hasFieldErrors()) {
			System.out.println("field errors");
		}else {
		
		Long realID = Long.parseLong(clientID);
		Client client = clientService.findById(realID).orElseThrow(() -> new RuntimeException("No such client!"));
		project.setClient(client);
		System.out.println(project.getName());
		projectService.saveProject(project);
		
		
		}
		return "redirect:/lists/projectList";
	}
	
	@GetMapping(value="add/addPiece")
	public String addPieceGet(Model model) {
		Iterable<Project> projects = projectService.findAll();
		
		model.addAttribute("projects",  projects);
		model.addAttribute("piece", new Piece());
		
		return "add/addPiece";
	}
	
	@PostMapping(value="add/addPiece")
	public String addPiecePost(@Valid Piece piece, BindingResult result, @RequestParam("projectID") String projectID, Model model) {
		
		Long realID = Long.parseLong(projectID);
		Project project = projectService.findById(realID).orElseThrow(()->new RuntimeException("No such project"));
		piece.getProjects().add(project);
		pieceService.savePiece(piece);
		return "redirect:/lists/pieceList";
	}
	
	@GetMapping(value="add/addInstrument")
	public String addInstrumentGet(Model model) {
		Iterable<InstFamily> instFamilies = instrumentService.findAllInstFamily();
		
		model.addAttribute("instFamilies", instFamilies);
		model.addAttribute("instrument", new Instrument());
		
		return "add/addInstrument";
	}
	
	@PostMapping(value="add/addInstrument")
	public String addInstrumentPost(@Valid Instrument instrument, BindingResult result, @RequestParam("instFamilyID") String instFamilyID, Model model) {
		Long realID = Long.parseLong(instFamilyID);
		InstFamily instFamily = instrumentService.readById(realID).orElseThrow(()->new RuntimeException("No such Instrument Family"));
		instrument.setInstFamily(instFamily);
		instrumentService.saveInstrument(instrument);
		return "redirect:/lists/instrumentList";
	}

}
