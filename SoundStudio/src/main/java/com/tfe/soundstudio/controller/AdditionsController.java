/**
 * 
 */
package com.tfe.soundstudio.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.Musician;
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
		
		return "add/addClient";
	}
	
	@PostMapping(value="add/addClient")
	public String addClientPost(@Valid Client client, BindingResult result, Model model) {
		
		return "clientList";
	}
	
	@GetMapping(value="add/addMusician")
	public String addMusicianGet(Model model) {
		
		model.addAttribute("musician", new Musician());
		
		return "add/addMusician";
	}
	
	@PostMapping(value="add/addMusician")
	public String addMusicianPost(@Valid Musician musician, BindingResult result, Model model) {
		
		return "musicianList";
	}
	
	@GetMapping(value="add/addEngineer")
	public String addEngineerGet(Model model) {
		
		model.addAttribute("engineer", new Engineer());
		
		return "add/addClient";
	}
	
	@PostMapping(value="add/addEngineer")
	public String addEngineerPost(@Valid Engineer engineer, BindingResult result, Model model) {
		
		return "engineerList";
	}

}
