package com.sprhib.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import liquibase.integration.spring.SpringLiquibase;

import com.sprhib.init.WebAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=WebAppConfig.class)
@TestPropertySource("classpath:applicationTest.properties")
public class TeamControllerTest extends JUnitBase {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private SpringLiquibase springLiquibase;

	private MockMvc mockMvc;
	private JdbcTemplate template;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		template = database(springLiquibase);
		databasePrepare(template);
	}
	
	@After
	public void cleanUp() {
		databaseClean(template);
	}
	
	@Test
	public void testTeamListPage() throws Exception {
		final String[] urls =  {"/team", "/team/list"};
		for (String s: urls) {
			mockMvc.perform(get(s))
				.andExpect(status().isOk())
				.andExpect(view().name("list-of-teams"))
				.andExpect(model().attributeExists("teams"))
				.andExpect(model().attribute("page", "team"));
		}
	}
	
	@Test
	public void testTeamAddPage() throws Exception {
		mockMvc.perform(get("/team/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-team-form"))
			.andExpect(model().attributeExists("organizations"))
			.andExpect(model().attributeExists("team"))
			.andExpect(model().attribute("page", "team"));
		mockMvc.perform(post("/team/add").param("name", "WorstTeamEver").param("rating", "10").param("organization", "2"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testTeamAddPageWithoutOrganizations() throws Exception {
		template.execute("TRUNCATE TABLE organizations;");
		mockMvc.perform(get("/team/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testTeamEditPage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/team/edit/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("edit-team-form"))
			.andExpect(model().attributeExists("organizations"))
			.andExpect(model().attributeExists("team"))
			.andExpect(model().attribute("page", "team"));
		mockMvc.perform(post("/team/edit/{id}", id).param("name", "WorstTeamEver").param("rating", "10").param("organization", "1"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testTeamEditPageWithoutOrganizations() throws Exception {
		final int id = 1;
		template.execute("TRUNCATE TABLE organizations;");
		mockMvc.perform(get("/team/edit/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testTeamDeletePage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/team/delete/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
}
