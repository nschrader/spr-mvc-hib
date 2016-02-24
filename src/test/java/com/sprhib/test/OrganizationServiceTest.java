package com.sprhib.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sprhib.dao.TeamDAO;
import com.sprhib.dao.OrganizationDAO;
import com.sprhib.service.OrganizationService;
import com.sprhib.service.OrganizationServiceImpl;
import com.sprhib.model.Team;
import com.sprhib.model.Organization;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceTest {

	@Mock
	private OrganizationDAO organizationDAO;
	
	@Mock
	private TeamDAO teamDAO;
	
	@InjectMocks
	private OrganizationService organizationService = new OrganizationServiceImpl();
	
	private Organization testOrganization;
	
	@Before
	public void init() {
		testOrganization = new Organization();
		testOrganization.setName("VA Linux");
		List<Organization> testOrganizationList = new ArrayList<Organization>();
		testOrganizationList.add(testOrganization);
		when(organizationDAO.getOrganization(anyInt())).thenReturn(testOrganization);
		when(organizationDAO.getOrganizations()).thenReturn(testOrganizationList);
		when(teamDAO.getTeamsByOrganization(anyInt())).thenReturn(new ArrayList<Team>());
	}
	
	@Test
	public void getOrganizationTest() throws Exception {
		assertEquals(organizationService.getOrganization(1).getName(), testOrganization.getName());
		verify(organizationDAO, times(1)).getOrganization(anyInt());
	}
	
	@Test
	public void addOrganizationTest() throws Exception {
		organizationService.addOrganization(new Organization());
		verify(organizationDAO, times(1)).addOrganization(any(Organization.class));
	}
	
	@Test
	public void getOrganizationsTest() throws Exception {
		assertEquals(organizationService.getOrganizations().size(), 1);
		verify(organizationDAO, times(1)).getOrganizations();
	}
	
	@Test
	public void updateOrganizationTest()throws Exception  {
		organizationService.updateOrganization(new Organization());
		verify(organizationDAO, times(1)).updateOrganization(any(Organization.class));
	}
	
	@Test
	public void deleteOrganizationTest() throws Exception {
		organizationService.deleteOrganization(1);
		verify(organizationDAO, times(1)).deleteOrganization(anyInt());
	}
}