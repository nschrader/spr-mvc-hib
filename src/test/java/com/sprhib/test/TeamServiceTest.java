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
import com.sprhib.dao.MemberDAO;
import com.sprhib.service.TeamService;
import com.sprhib.service.TeamServiceImpl;
import com.sprhib.model.Team;
import com.sprhib.model.Member;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest extends JUnitBase {

	@Mock
	private TeamDAO teamDAO;
	
	@Mock
	private MemberDAO memberDAO;
	
	@InjectMocks
	private TeamService teamService = new TeamServiceImpl();
	
	private Team testTeam;
	
	@Before
	public void init() {
		testTeam = teamCreate();
		List<Team> testTeamList = new ArrayList<Team>();
		testTeamList.add(testTeam);
		when(teamDAO.getTeam(anyInt())).thenReturn(testTeam);
		when(teamDAO.getTeams()).thenReturn(testTeamList);
		when(teamDAO.getTeamsByOrganization(anyInt())).thenReturn(testTeamList);
		when(memberDAO.getMembersByTeam(anyInt())).thenReturn(new ArrayList<Member>());
	}
	
	@Test
	public void getTeamTest() throws Exception {
		assertTeamsEqual(teamService.getTeam(1), testTeam);
		verify(teamDAO, times(1)).getTeam(anyInt());
	}
	
	@Test
	public void addTeamTest() throws Exception {
		teamService.addTeam(teamCreate());
		verify(teamDAO, times(1)).addTeam(any(Team.class));
	}
	
	@Test
	public void getTeamsTest() throws Exception {
		assertEquals(teamService.getTeams().size(), 1);
		verify(teamDAO, times(1)).getTeams();
	}
	
	@Test
	public void updateTeamTest()throws Exception  {
		teamService.updateTeam(teamCreate());
		verify(teamDAO, times(1)).updateTeam(any(Team.class));
	}
	
	@Test
	public void deleteTeamTest() throws Exception {
		teamService.deleteTeam(1);
		verify(teamDAO, times(1)).deleteTeam(anyInt());
	}
	
	@Test
	public void getTeamsByOrganizationTest() throws Exception {
		assertEquals(teamService.getTeamsByOrganization(1).size(), 1);
		verify(teamDAO, times(1)).getTeamsByOrganization(anyInt());
	}
}