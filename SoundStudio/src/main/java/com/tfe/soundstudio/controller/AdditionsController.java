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
		
		return "add/addClient";
	}
	
	@PostMapping(value="add/addClient")
	public String addClientPost(@Valid Client client, BindingResult result, Model model) {
		clientService.saveClient(client);
		return "clientList";
	}
	
	@GetMapping(value="add/addMusician")
	public String addMusicianGet(Model model) {
		
		model.addAttribute("musician", new Musician());
		
		return "add/addMusician";
	}
	
	@PostMapping(value="add/addMusician")
	public String addMusicianPost(@Valid Musician musician, BindingResult result, Model model) {
		musicianService.saveMusician(musician);
		return "musicianList";
	}
	
	@GetMapping(value="add/addEngineer")
	public String addEngineerGet(Model model) {
		
		model.addAttribute("engineer", new Engineer());
		
		return "add/addClient";
	}
	
	@PostMapping(value="add/addEngineer")
	public String addEngineerPost(@Valid Engineer engineer, BindingResult result, Model model) {
		engineerService.saveEngineer(engineer);
		return "engineerList";
	}
	
	@GetMapping(value="add/addProject")
	public String addProjectGet(Model model) {
		
		model.addAttribute("project", new Project());
		
		return "add/addProject";
	}
	
	@PostMapping(value="add/addProject")
	public String addProjectPost(@Valid Project project, BindingResult result, Model model) {
		projectService.saveProject(project);
		return "projectList";
	}
	
	@GetMapping(value="add/addPiece")
	public String addPieceGet(Model model) {
		
		model.addAttribute("piece", new Piece());
		
		return "add/addPiece";
	}
	
	@PostMapping(value="add/addPiece")
	public String addPiecePost(@Valid Piece piece, BindingResult result, Model model) {
		pieceService.savePiece(piece);
		return "pieceList";
	}
	
	@GetMapping(value="add/addInstrument")
	public String addInstrumentGet(Model model) {
		
		model.addAttribute("instrument", new Instrument());
		
		return "add/addInstrument";
	}
	
	@PostMapping(value="add/addInstrument")
	public String addInstrumentPost(@Valid Instrument instrument, BindingResult result, Model model) {
		instrumentService.saveInstrument(instrument);
		return "instrumentList";
	}

}
