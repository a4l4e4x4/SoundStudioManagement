/**
 * 
 */
package com.tfe.soundstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tfe.soundstudio.service.MusicianService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class DeleteController {
	
	private MusicianService musicianService;

	public DeleteController(MusicianService musicianService) {
		super();
		this.musicianService = musicianService;
	}
	
	
	@PostMapping(value="deleteMusician")
	public String deleteMusician(String musID,Model model) {
		System.out.println("test");
		Long realID = Long.parseLong(musID);
		musicianService.deleteById(realID);
		System.out.println(musID);
		
		return "redirect:/lists/musicianList";
	}

}
