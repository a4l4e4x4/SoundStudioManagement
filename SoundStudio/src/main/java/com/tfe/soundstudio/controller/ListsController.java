/**
 * 
 */
package com.tfe.soundstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.Musician;
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
public class ListsController {

	private final ClientService clientService;
	private final EngineerService engineerService;
	private final MusicianService musicianService;
	private final InstrumentService instrumentService;
	private final ProjectService projectService;
	private final PieceService pieceService;

	public ListsController(ClientService clientService, EngineerService engineerService,
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

	@GetMapping(value = "lists/clientList")
	public String listClientsGet(Model model) {

		Iterable<Client> clients = clientService.findAll();
		
		model.addAttribute("clients", clients);

		return "lists/clientList";
	}
	
	@GetMapping(value = "lists/musicianList")
	public String listMusiciansGet(Model model) {

		Iterable<Musician> musicians = musicianService.findAll();
		/*for (Musician mus : musicians) {
			System.out.println(mus.getName());
		} */
		

		model.addAttribute("musicians", musicians);

		return "lists/musicianList";
	}
	
	@GetMapping(value = "lists/engineerList")
	public String listEngineersGet(Model model) {

		Iterable<Engineer> engineers = engineerService.findAll();
		
		model.addAttribute("engineers", engineers);

		return "lists/engineerList";
	}
	
	@GetMapping(value = "lists/projectList")
	public String listProjectsGet(Model model) {

		Iterable<Project> projects = projectService.findAll();
		
		model.addAttribute("projects", projects);

		return "lists/projectList";
	}

}
