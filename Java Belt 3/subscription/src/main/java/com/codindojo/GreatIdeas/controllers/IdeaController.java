package com.codindojo.GreatIdeas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codindojo.GreatIdeas.models.Dig;
import com.codindojo.GreatIdeas.models.Idea;
import com.codindojo.GreatIdeas.models.User;
import com.codindojo.GreatIdeas.services.IdeaService;
import com.codindojo.GreatIdeas.services.DigService;
import com.codindojo.GreatIdeas.services.UserService;

@Controller
public class IdeaController {
	@Autowired
	private UserService userService;
	@Autowired
	private DigService digService;
	@Autowired
	private IdeaService ideaService;




	 // landing
	@RequestMapping("/ideas")
	public String dashboard(HttpSession session, Idea idea, Model model, RedirectAttributes redir) {
		if (session.getAttribute("id") != null) {
			User user = userService.findById((Long) session.getAttribute("id"));
			Long userId = (Long) session.getAttribute("id");

			if (user != null) {

				User u = userService.findById(userId);
				model.addAttribute("user", u);
				model.addAttribute("user", user);
                List<Idea> ideas = ideaService.getAllIdeas();
				model.addAttribute("ideas", ideas);
				return "dashboard";
			} else {
				return "redirect:/";
			}
		} else {
			redir.addFlashAttribute("loginerror", "You must be logged in to enter this website");
			return "redirect:/";
		}
	}

	// ideas create form
	@RequestMapping("/ideas/new")
	public String addshow(HttpSession session, @ModelAttribute("newIdea")Idea idea, RedirectAttributes redir){
		if (session.getAttribute("id") != null) {
			return "newIdea";
		} else {
			redir.addFlashAttribute("loginerror", "You must be logged in to enter this website");
			return "redirect:/";
		}
	}

	// Idea post page 
	@RequestMapping(value = "/create", method = RequestMethod.POST) 
	public String create(@Valid @ModelAttribute("newMediaShow") Idea idea, BindingResult result,
			HttpSession session, Model model, RedirectAttributes redir) {
			Long userId = (Long) session.getAttribute("id");
		if (result.hasErrors()) {
			redir.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/ideas/new";
		} else {
			User u = userService.findById(userId);
			ideaService.createIdea(idea, u);
			return "redirect:/ideas";
		}
	}


	//this route renders the show information about that show 
	@RequestMapping("/ideas/{id}")
	public String aboutShow(HttpSession session, @PathVariable(value="id") Long id, Idea idea, Model model, RedirectAttributes redir, @ModelAttribute("newRating")Dig dig) {
		if (session.getAttribute("id") != null){
				Idea p = ideaService.getIdea(id);
				model.addAttribute("idea", p);
				return "about";

		} else {
			redir.addFlashAttribute("loginerror", "You must be logged in to enter this website");
			return "redirect:/";
		}
	}


	//update
	@RequestMapping(value="ideas/update/{id}", method=RequestMethod.POST)
	public String editEvent(@Valid @ModelAttribute("editMediaShow") Idea idea, BindingResult result, @PathVariable(value="id") Long id, Model model,  RedirectAttributes redir) {
		if(result.hasErrors()){
			redir.addFlashAttribute("errors", "Something went sideways..Jk no blanks!");
			Idea p = ideaService.getIdea(id);
			model.addAttribute("editMediaShow", p);
			return "redirect:/ideas/edit/{id}";
		}

		else 
			ideaService.updateIdea(idea);
			return "redirect:/ideas";

	} 
	
 //edit that show
	 @RequestMapping("ideas/edit/{id}")
	 public String viewEvent(HttpSession session, @PathVariable(value="id") Long id, RedirectAttributes redir, Model model) {
		 if (session.getAttribute("id") != null) {
			 Idea i = ideaService.getIdea(id);
			 model.addAttribute("editMediaShow", i);
			 return "editSub";
		 }
		 redir.addFlashAttribute("loginerror", "You must be logged in to enter the webpage");
		 return "redirect:/";
	 }  
  
 

	// This is the delete route 
	
	@GetMapping("/delete/{id}")
		public String delete(@PathVariable(value="id") Long id) {
		ideaService.deleteIdea(id);
		return "redirect:/ideas";
	}


	
	/// this is the "Likes section"

	@RequestMapping("/{id}/like")
	public String join(@PathVariable(value="id") Long id, HttpSession session, RedirectAttributes redir) {
		System.out.println("testing");
		Long userId = (Long) session.getAttribute("id");
		if (userId != null) {
			User user = userService.findById(userId);
			Idea idea = ideaService.getIdea(id);
			ideaService.digIdea(idea, user);		
			return "redirect:/ideas";
		}
		redir.addFlashAttribute("loginerror", "You must be logged in to perform this action");
		return "redirect:/";
	}
	

	@RequestMapping("/{id}/unlike")
	public String cancel(@PathVariable(value="id") Long id, HttpSession session, RedirectAttributes redir){
		Long userId = (Long) session.getAttribute("id");
		System.out.println("What the hell");
		if (userId != null) {
			User user = userService.findById(userId);
			Idea idea =  ideaService.getIdea(id);
			ideaService.undigIdea(idea, user);	
			return "redirect:/ideas";
		}
		redir.addFlashAttribute("loginerror", "You must be logged in to peform this action");
		return "redirect:/";
	}


}