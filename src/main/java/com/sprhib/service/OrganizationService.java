package com.sprhib.service;

import java.util.List;

import com.sprhib.model.Organization;

public interface OrganizationService {
	
	public void addOrganization(Organization organization);
	public void updateOrganization(Organization organization);
	public Organization getOrganization(int id);
	public void deleteOrganization(int id) throws Exception;
	public List<Organization> getOrganizations();

}
