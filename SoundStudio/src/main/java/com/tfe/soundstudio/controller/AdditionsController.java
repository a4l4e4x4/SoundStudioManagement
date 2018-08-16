/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
		Iterable<Client> allClients = clientService.findAll();
		for (Client client2 : allClients) {
			if(client.getName().equalsIgnoreCase(client2.getName())){
				return "redirect:/lists/clientList";
			}
		}
		
		client.setContact(contact);
		clientService.saveClient(client);
		return "redirect:/lists/clientList";
	}
	
	@GetMapping(value="add/addMusician")
	public String addMusicianGet(@RequestParam(value="musID", required=false) String musID, Model model) {
		Set<Long> instID = new HashSet<>();
		/*
		 * if its an update
		 * musID get passed
		 */
		if (musID != null) {
			System.out.println(musID);
			//convert from String to Long
			Long realID = Long.parseLong(musID);
			//find the musician 
			Musician musician = musicianService.findById(realID).orElseThrow(() -> new RuntimeException("No such musician!"));
			//add the IDs of played instruments
			musician.getInstruments().forEach(e->instID.add(e.getId()));
			//add the musician to the model
			model.addAttribute("existing_musician", musician);
			//pass the ID from musician to update
			model.addAttribute("musID",  musID);
		}

		Set<Instrument> woodwinds = instrumentService.findByInstFamilyFamily("woodwinds");
		Set<Instrument> strings = instrumentService.findByInstFamilyFamily("strings");
		Set<Instrument> brass = instrumentService.findByInstFamilyFamily("brass");
		Set<Instrument> keyboards = instrumentService.findByInstFamilyFamily("keyboards");
		Set<Instrument> percussions = instrumentService.findByInstFamilyFamily("percussion");
		
		
		
		model.addAttribute("instID", instID);
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
	public String addMusicianPost(@Valid Musician musician, BindingResult result, @Valid Contact contact, BindingResult result2, @RequestParam(value="instID", required=false) HashSet<String> instIDs, @RequestParam(value="musID", required=false) String musID, Model model) {
		Set<Instrument> instList = new HashSet<>();
		/*
		 * if list of played instruments is passed, create instrument list
		 */
		if(instIDs != null) {
			for(String id : instIDs) {
				Long realID = Long.parseLong(id);
				Instrument inst = instrumentService.findById(realID).orElseThrow(() -> new RuntimeException("No such musician!"));
				
				instList.add(inst);
				//System.out.println(id);
			}
		}
		
		/*
		 * if existing musicians ID is passed
		 * update musician
		 */
		if(musID != null) {
			Long realID = Long.parseLong(musID);
			Musician old_musician = musicianService.findById(realID).orElseThrow(()-> new RuntimeException("Wrong musician ID" + realID));
			old_musician.setInstruments(instList);
			old_musician.setContact(contact);
			old_musician.setName(musician.getName());
			old_musician.setSurname(musician.getSurname());
			System.out.println(musID);
			System.out.println(old_musician.getContact().getContactname());
			musicianService.saveMusician(old_musician);
			
		}else {
			/*
			 * if musician doesn't exist, save musician with played instruments
			 */
			musician.setContact(contact);
			musicianService.saveMusician(musician);
		}
		
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
		engineer.setContact(contact);
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
