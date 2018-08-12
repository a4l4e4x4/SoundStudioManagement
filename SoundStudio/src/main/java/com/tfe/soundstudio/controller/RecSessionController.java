/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfe.soundstudio.model.RecSession;
import com.tfe.soundstudio.service.RecSessionService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class RecSessionController {
	
	private final RecSessionService recSessionService;

	public RecSessionController(RecSessionService recSessionService) {
		super();
		this.recSessionService = recSessionService;
	}
	
	@GetMapping(value="recsession")
	public String recSessionGet(Model model) {
		
		model.addAttribute("recsession", new RecSession());
		
		return "recsession";
	}
	
	@PostMapping(value="recsession")
	public String recSessionPost(@Valid RecSession recsession, BindingResult result, @RequestParam(value="duration") Integer duration, Model model) {
		
		/*RecSession recSession = new RecSession();
		recSession.setDuration(duration);
		Date date = new Date();
		recSession.setSessionDate(date);
		recSessionService.createRecSession(recSession); */
		Date date = new Date();
		recsession.setSessionDate(date);
		model.addAttribute("recsession", recsession);
		
		return "recsession_details";
	}

}
