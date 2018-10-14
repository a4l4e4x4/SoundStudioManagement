/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.tfe.soundstudio.model.EngineerFee;
import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.model.Piece;
import com.tfe.soundstudio.model.Project;
import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.RecSession;
import com.tfe.soundstudio.model.SessionFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.model.TrackObject;
import com.tfe.soundstudio.model.TrackObjectFile;
import com.tfe.soundstudio.service.ClientService;
import com.tfe.soundstudio.service.EngineerService;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.MusicianService;
import com.tfe.soundstudio.service.PieceService;
import com.tfe.soundstudio.service.ProjectService;
import com.tfe.soundstudio.service.RecSessionService;
import com.tfe.soundstudio.service.TrackObjectFileService;
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
	private final ProjectService projectService;
	private final PieceService pieceService;
	private final TrackObjectFileService trackObjectFileService;

	public RecSessionController(RecSessionService recSessionService, ClientService clientService,
			EngineerService engineerService, MusicianService musicianService, InstrumentService instrumentService,
			ProjectService projectService, PieceService pieceService, TrackService trackService,
			TrackObjectFileService trackObjectFileService) {
		super();
		this.recSessionService = recSessionService;
		this.clientService = clientService;
		this.engineerService = engineerService;
		this.musicianService = musicianService;
		this.projectService = projectService;
		this.pieceService = pieceService;
		this.trackObjectFileService = trackObjectFileService;
	}

	@GetMapping(value = "recsession")
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

	@PostMapping(value = "recsession")
	public String recSessionPost(@Valid RecSession recsession, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "engineerID", required = false) String engineerID,
			@RequestParam(value = "pieceID", required = false) String pieceID, Model model) {
		// if file exists
		if (file != null) {
			try {
				// Get size
				byte[] bytes = file.getBytes();
				// Put it in a temp directory
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);

				// prepare to read file
				ReadFile newImport = new ReadFile();
				// transform path in String
				String filepath = path.toString();
				// pass file path as string to be readed
				newImport.ScanIt(filepath);
				// get the tracks
				List<Track> tracks = newImport.getTracks();
				// trackService.saveTracks(tracks);
				// Get a list of TrackObjectFiles
				Set<TrackObjectFile> trackObjectFiles = new HashSet<>();
				for (Track track : tracks) {
					for (TrackObject tobj : track.getObjectList()) {
						trackObjectFiles.add(tobj.getTrackObjectFile());
					}
				}
				// Get the SessionFile
				SessionFile sf = newImport.getSessionFile();
				// Put the tracks in ti
				sf.setTracks(tracks);
				//if Piece exists, put it in Session File
				if (pieceID != null) {
					Long realPieceID = Long.parseLong(pieceID);
					Piece piece = pieceService.findById(realPieceID);
					model.addAttribute("piece", piece);
					sf.getPieces().add(piece);
					
				}
				// put the SessionFile in RecSession
				recsession.getSessionFiles().add(sf);
				// Save to Database
				
				// find Musicians
				Iterable<Musician> musicians = musicianService.findAll();
				// Pass to model
				model.addAttribute("musicians", musicians);
				model.addAttribute("trackObjectFiles", trackObjectFiles);
				model.addAttribute("newtracks", tracks);
				model.addAttribute("sessionfile", sf);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (engineerID != null) {
			//System.out.println(engineerID);
			Long realEnginnerID = Long.parseLong(engineerID);
			Engineer engineer = engineerService.findById(realEnginnerID);
			EngineerFee engineerFee = new EngineerFee();
			engineerFee.setEngineer(engineer);
			engineerFee.setFee(200.0);
			engineerFee.setRecsession(recsession);
			recsession.getEngineerFees().add(engineerFee);
			
			model.addAttribute("engineer", engineer);
		}
		
		Date date = new Date();
		recsession.setSessionDate(date);
		Set<Long> tofID = new HashSet<>();
		List<Long> musicianID = new ArrayList<>();
		model.addAttribute("tofID",  tofID);
		model.addAttribute("musicianID", musicianID);
		model.addAttribute("recsession", recsession);
		recSessionService.createRecSession(recsession);

		return "recsession_details";
	}

	@PostMapping(value = "recSessionSave")
	public String recSessionSave(@RequestParam(value = "tofID", required = false) Set<String> tofID,
			@RequestParam(value = "musicianID", required = false) List<String> musicianID, Model model) {

		
		if(tofID == null) {System.out.println("no tof");}
		if(musicianID == null) {System.out.println("no mus");}
		HashSet<TrackObjectFile> trackObjectFiles = new HashSet<>();
		
		if (tofID != null && musicianID != null) {
			TrackObjectFile tof = new TrackObjectFile();
			Musician musician = new Musician();
			List<Long> realMusIds = new ArrayList<>();
			for (String musID : musicianID) {
				System.out.println(musID);
				Long realMusId = Long.parseLong(musID);
				realMusIds.add(realMusId);
			}
			int count = 0;
			for (String tofIDstr : tofID) {
				
				Long realTofID = Long.parseLong(tofIDstr);
				System.out.println(tofIDstr);
				tof = trackObjectFileService.findById(realTofID);
				//System.out.println(tof.getFileLocation());
				musician = musicianService.findById(realMusIds.get(count));
				//System.out.println(musician.getName());
				tof.getMusicians().add(musician);
				trackObjectFileService.saveTrackObjectFile(tof);
				trackObjectFiles.add(tof);
				count += 1;
			}
		}
		model.addAttribute("tofs", trackObjectFiles);
		

		return "recsession_final";
	}

}
