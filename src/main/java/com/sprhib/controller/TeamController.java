package com.sprhib.controller;

import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Team;
import com.sprhib.service.TeamService;
import com.sprhib.model.Organization;
import com.sprhib.service.OrganizationService;

@Controller
@RequestMapping(value="/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView;
		List<Organization> organizations = organizationService.getOrganizations();
		if (organizations.isEmpty()) {
			modelAndView = new ModelAndView("home");
			modelAndView.addObject("message", messages.getMessage("tC.added.error", null, LocaleContextHolder.getLocale()));	
			modelAndView.addObject("page", "home");
		} else {
			modelAndView = new ModelAndView("add-team-form");
			modelAndView.addObject("organizations", organizations);
			modelAndView.addObject("team", new Team());
			modelAndView.addObject("page", "team");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingTeam(HttpServletRequest request) throws Exception {
		Map<String, String[]> post = request.getParameterMap();
		Team team = new Team();
		team.setName(post.get("name")[0]);
		team.setRating(Integer.valueOf(post.get("rating")[0]));
		team.setOrganization(organizationService.getOrganization(Integer.valueOf(post.get("organization")[0])));
		if (team.getOrganization() == null)
			throw new Exception();
		teamService.addTeam(team);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", messages.getMessage("tC.added.success", null, LocaleContextHolder.getLocale()));	
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("list-of-teams");
		modelAndView.addObject("teams", teamService.getTeams());
		modelAndView.addObject("page", "team");
		return modelAndView;
	}
	
	@RequestMapping(value="")
	public ModelAndView indexTeam() {
	      return listOfTeams();
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView;
		List<Organization> organizations = organizationService.getOrganizations();
		if (organizations.isEmpty()) {
			modelAndView = new ModelAndView("home");
			modelAndView.addObject("message", messages.getMessage("tC.edited.error", null, LocaleContextHolder.getLocale()));	
			modelAndView.addObject("page", "home");
		} else {
			modelAndView = new ModelAndView("edit-team-form");
			Team team = teamService.getTeam(id);
			modelAndView.addObject("organizations", organizationService.getOrganizations());
			modelAndView.addObject("team", team);
			modelAndView.addObject("page", "team");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTeam(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		Map<String, String[]> post = request.getParameterMap();
		Team team = teamService.getTeam(id);
		team.setName(post.get("name")[0]);
		team.setRating(Integer.valueOf(post.get("rating")[0]));
		team.setOrganization(organizationService.getOrganization(Integer.valueOf(post.get("organization")[0])));
		if (team.getOrganization() == null)
			throw new Exception();
		teamService.updateTeam(team);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", messages.getMessage("tC.edited.success", null, LocaleContextHolder.getLocale()));
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		try {
			teamService.deleteTeam(id);
			modelAndView.addObject("message", messages.getMessage("tC.deleted.success", null, LocaleContextHolder.getLocale()));
		} catch (Exception e) {
			modelAndView.addObject("message", messages.getMessage("tC.deleted.error", null, LocaleContextHolder.getLocale()));
		}
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
}
