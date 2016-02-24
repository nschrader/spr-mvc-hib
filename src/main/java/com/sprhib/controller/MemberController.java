package com.sprhib.controller;

import java.util.Map;
import java.util.ArrayList;
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

import com.sprhib.model.Member;
import com.sprhib.service.MemberService;
import com.sprhib.model.Team;
import com.sprhib.service.TeamService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addMemberPage() {
		ModelAndView modelAndView = new ModelAndView("add-member-form");
		modelAndView.addObject("allTeams", teamService.getTeams());
		modelAndView.addObject("member", new Member());
		modelAndView.addObject("page", "member");
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingMember(HttpServletRequest request) throws Exception {
		Map<String, String[]> post = request.getParameterMap();
		Member member = new Member();
		member.setName(post.get("name")[0]);
		ArrayList<Team> teams = new ArrayList<Team>();
		for (String s: post.get("teams"))
			teams.add(teamService.getTeam(Integer.valueOf(s)));
		member.setTeams(teams);
		if (member.getTeams() == null)
			throw new Exception();
		memberService.addMember(member);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", messages.getMessage("mC.added", null, LocaleContextHolder.getLocale()));	
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfMember() {
		ModelAndView modelAndView = new ModelAndView("list-of-members");
		modelAndView.addObject("members", memberService.getMembers());
		modelAndView.addObject("page", "member");
		return modelAndView;
	}
	
	@RequestMapping(value="")
	public ModelAndView indexMember() {
	      return listOfMember();
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editMemberPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-member-form");
		Member member = memberService.getMember(id);
		modelAndView.addObject("allTeams", teamService.getTeams());
		modelAndView.addObject("member", member);
		modelAndView.addObject("page", "member");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingMember(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		Map<String, String[]> post = request.getParameterMap();
		Member member = memberService.getMember(id);
		member.setName(post.get("name")[0]);
		ArrayList<Team> teams = new ArrayList<Team>();
		for (String s: post.get("teams"))
			teams.add(teamService.getTeam(Integer.valueOf(s)));
		member.setTeams(teams);
		if (member.getTeams() == null)
			throw new Exception();
		memberService.updateMember(member);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", messages.getMessage("mC.edited", null, LocaleContextHolder.getLocale()));
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteMember(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		memberService.deleteMember(id);
		modelAndView.addObject("message", messages.getMessage("mC.deleted", null, LocaleContextHolder.getLocale()));
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
}
