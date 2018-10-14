/**
 * 
 */
package com.tfe.soundstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
public class DeleteController {
	
	private MusicianService musicianService;
	private EngineerService engineerService;
	private PieceService pieceService;
	private ProjectService projectService;
	private ClientService clientService;
	private InstrumentService instrumentService;
	

	
	
	
	public DeleteController(MusicianService musicianService, EngineerService engineerService, PieceService pieceService,
			ProjectService projectService, ClientService clientService, InstrumentService instrumentService) {
		super();
		this.musicianService = musicianService;
		this.engineerService = engineerService;
		this.pieceService = pieceService;
		this.projectService = projectService;
		this.clientService = clientService;
		this.instrumentService = instrumentService;
	}





	/**
	 * 
	 * @param musID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deleteMusician")
	public String deleteMusician(String musID,Model model) {
		
		Long realID = Long.parseLong(musID);
		musicianService.deleteById(realID);
		System.out.println(musID);
		
		return "redirect:/lists/musicianList";
	}
	
	/**
	 * 
	 * @param engID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deleteEngineer")
	public String deleteEngineer(String engID, Model model) {
		
		Long realID = Long.parseLong(engID);
		engineerService.deleteById(realID);
		
		return "redirect:/lists/engineerList";
	}

	/**
	 * 
	 * @param pieceID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deletePiece")
	public String deletePiece(String pieceID, Model model) {
		
		Long realID = Long.parseLong(pieceID);
		pieceService.deleteById(realID);
		
		return "redirect:/lists/pieceList";
	}
	
	/**
	 * 
	 * @param projectID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deleteProject")
	public String deleteProject(String projectID, Model model) {
		
		Long realID = Long.parseLong(projectID);
		projectService.deleteById(realID);
		
		return "redirect:/lists/projectList";
	}
	
	/**
	 * 
	 * @param clientID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deleteClient")
	public String deleteClient(String clientID, Model model) {
		
		Long realID = Long.parseLong(clientID);
		clientService.deleteById(realID);
		
		return "redirect:/lists/clientList";
	}

	/**
	 * 
	 * @param instID
	 * @param model
	 * @return
	 */
	@PostMapping(value="deleteInstrument")
	public String deleteInstrument(String instID, Model model) {
		
		Long realID = Long.parseLong(instID);
		instrumentService.deleteById(realID);
		
		return "redirect:/lists/instrumentList";
	}
}
