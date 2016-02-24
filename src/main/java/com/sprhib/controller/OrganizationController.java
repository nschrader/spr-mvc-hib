package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Organization;
import com.sprhib.service.OrganizationService;

@Controller
@RequestMapping(value="/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOrganizationPage() {
		ModelAndView modelAndView = new ModelAndView("add-organization-form");
		modelAndView.addObject("organization", new Organization());
		modelAndView.addObject("page", "organization");
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingOrganization(@ModelAttribute Organization organization) {		
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.addOrganization(organization);
		modelAndView.addObject("message", messages.getMessage("oC.added", null, LocaleContextHolder.getLocale()));	
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfOrganizations() {
		ModelAndView modelAndView = new ModelAndView("list-of-organizations");	
		modelAndView.addObject("organizations", organizationService.getOrganizations());
		modelAndView.addObject("page", "organization");
		return modelAndView;
	}
	
	@RequestMapping(value="")
	public ModelAndView indexOrganizations() {
	      return listOfOrganizations();
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOrganizationPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-organization-form");
		Organization organization = organizationService.getOrganization(id);
		modelAndView.addObject("organization", organization);
		modelAndView.addObject("page", "organization");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingOrganization(@ModelAttribute Organization organization, @PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.updateOrganization(organization);
		modelAndView.addObject("message", messages.getMessage("oC.edited", null, LocaleContextHolder.getLocale()));
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOrganization(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		try {
			organizationService.deleteOrganization(id);
			modelAndView.addObject("message", messages.getMessage("oC.deleted.success", null, LocaleContextHolder.getLocale()));
		} catch (Exception e) {
			modelAndView.addObject("message", messages.getMessage("oC.deleted.error", null, LocaleContextHolder.getLocale()));
		}		
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
}
