package com.sprhib.test;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import liquibase.integration.spring.SpringLiquibase;

import com.sprhib.init.WebAppConfig;
import com.sprhib.model.Team;
import com.sprhib.dao.TeamDAO;
import com.sprhib.dao.TeamDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=WebAppConfig.class)
@TestPropertySource("classpath:applicationTest.properties")
@Transactional
public class TeamDAOTest extends JUnitBase {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private SpringLiquibase springLiquibase;

	private JdbcTemplate template;
		
	@Before
	public void init() {
		template = database(springLiquibase);
		databasePrepare(template);
	}
	
	@After
	public void cleanUp() {
		databaseClean(template);
	}
	
	/*
	 * Tests also getTeams
	 */
	@Test
	public void addTeamTest() throws Exception {
		Team testTeam = teamCreate();
		testTeam.setOrganization(teamDAO.getTeam(1).getOrganization());
		int size = teamDAO.getTeams().size();
		teamDAO.addTeam(testTeam);
		List<Team> teams = teamDAO.getTeams();
		assertEquals(teams.size(), size+1);
		Team t = teams.get(size);
		assertTeamsEqual(t, testTeam);
	}
	
	@Test
	public void getDatabaseTeam() throws Exception {
		Team t = teamDAO.getTeam(1);
		assertEquals(t.getName(), "BestTeamEver");
		assertEquals(t.getRating(), (Integer) 20);
		assertEquals(t.getOrganization().getId(), (Integer) 1);
	}
	
	@Test
	public void deleteDatabaseTeam() throws Exception {
		int size = teamDAO.getTeams().size();
		teamDAO.deleteTeam(1);
		assertEquals(teamDAO.getTeams().size(), size-1);
	}
	
	@Test
	public void updateDatabaseTeam() throws Exception {
		int size = teamDAO.getTeams().size();
		Team t = teamDAO.getTeam(1);
		t.setName("Bad Team");
		t.setRating(0);
		teamDAO.updateTeam(t);
		assertEquals(teamDAO.getTeams().size(), size);
		assertTeamsEqual(teamDAO.getTeam(1), t);
	}
	
	@Test 
	public void getDatabaseTeamsByOrganisation() throws Exception {
		assertEquals(teamDAO.getTeamsByOrganization(1).size(), 2);
		assertEquals(teamDAO.getTeamsByOrganization(2).size(), 0);
	}
}
