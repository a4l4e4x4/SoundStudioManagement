/**
 * 
 */
package com.tfe.soundstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
public class EditController {
	
	private final ClientService clientService;
	private final EngineerService engineerService;
	private final MusicianService musicianService;
	private final InstrumentService instrumentService;
	private final ProjectService projectService;
	private final PieceService pieceService;
	public EditController(ClientService clientService, EngineerService engineerService, MusicianService musicianService,
			InstrumentService instrumentService, ProjectService projectService, PieceService pieceService) {
		super();
		this.clientService = clientService;
		this.engineerService = engineerService;
		this.musicianService = musicianService;
		this.instrumentService = instrumentService;
		this.projectService = projectService;
		this.pieceService = pieceService;
	}
	
	
	@GetMapping(value="edits/editMusician")
	public String editMusicianGet(String musId, Model model) {
		Long realID = Long.parseLong(musId);
		Musician musician = musicianService.findById(realID).orElseThrow(()->new RuntimeException("No such musician"));
		
		model.addAttribute("musician",  musician);
		
		return "edits/editMusician";
	}

}
