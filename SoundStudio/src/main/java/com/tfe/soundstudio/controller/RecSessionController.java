/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.model.Piece;
import com.tfe.soundstudio.model.Project;
import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.RecSession;
import com.tfe.soundstudio.model.SessionFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.service.ClientService;
import com.tfe.soundstudio.service.EngineerService;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.MusicianService;
import com.tfe.soundstudio.service.PieceService;
import com.tfe.soundstudio.service.ProjectService;
import com.tfe.soundstudio.service.RecSessionService;
import com.tfe.soundstudio.service.TrackService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class RecSessionController {
	
	private static String UPLOADED_FOLDER = "/tmp/";
	
	private final RecSessionService recSessionService;
	private final ClientService clientService;
	private final EngineerService engineerService;
	private final MusicianService musicianService;
	private final InstrumentService instrumentService;
	private final ProjectService projectService;
	private final PieceService pieceService;
	private final TrackService trackService;

	
	
	

	public RecSessionController(RecSessionService recSessionService, ClientService clientService,
			EngineerService engineerService, MusicianService musicianService, InstrumentService instrumentService,
			ProjectService projectService, PieceService pieceService, TrackService trackService) {
		super();
		this.recSessionService = recSessionService;
		this.clientService = clientService;
		this.engineerService = engineerService;
		this.musicianService = musicianService;
		this.instrumentService = instrumentService;
		this.projectService = projectService;
		this.pieceService = pieceService;
		this.trackService = trackService;
	}

	@GetMapping(value="recsession")
	public String recSessionGet(Model model) {
		
		Iterable<Musician> musicians = musicianService.findAll();
		Iterable<Engineer> engineers = engineerService.findAll();
		Iterable<Project> projects = projectService.findAll();
		Iterable<Client> clients = clientService.findAll();
		Iterable<Piece> pieces = pieceService.findAll();
		
		
		model.addAttribute("musicians", musicians);
		model.addAttribute("engineers", engineers);
		model.addAttribute("projects", projects);
		model.addAttribute("clients", clients);
		model.addAttribute("pieces", pieces);
		
		model.addAttribute("recsession", new RecSession());
		
		return "recsession";
	}
	
	@PostMapping(value="recsession")
	public String recSessionPost(@Valid RecSession recsession, BindingResult result, @RequestParam(value="file") MultipartFile file, Model model) {
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			 Files.write(path, bytes);

			ReadFile newImport = new ReadFile();
			String filepath = path.toString();
			newImport.ScanIt(filepath);
			List<Track> tracks = newImport.getTracks();
			//trackService.saveTracks(tracks);
			
			SessionFile sf = newImport.getSessionFile();
			sf.setTracks(tracks);
			recsession.getSessionFiles().add(sf);
			
			recSessionService.createRecSession(recsession);
			
			
			model.addAttribute("newtracks", tracks);
			} catch (IOException e) {
	            e.printStackTrace();
	        }
		Date date = new Date();
		recsession.setSessionDate(date);
		model.addAttribute("recsession", recsession);
		
		return "recsession_details";
	}

}
