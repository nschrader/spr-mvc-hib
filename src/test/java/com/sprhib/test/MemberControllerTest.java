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
public class MemberControllerTest extends JUnitBase {
	
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
	public void testMemberListPage() throws Exception {
		final String[] urls =  {"/member", "/member/list"};
		for (String s: urls) {
			mockMvc.perform(get(s))
				.andExpect(status().isOk())
				.andExpect(view().name("list-of-members"))
				.andExpect(model().attributeExists("members"))
				.andExpect(model().attribute("page", "member"));
		}
	}
	
	@Test
	public void testMemberAddPage() throws Exception {
		mockMvc.perform(get("/member/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-member-form"))
			.andExpect(model().attributeExists("allTeams"))
			.andExpect(model().attributeExists("member"))
			.andExpect(model().attribute("page", "member"));
		mockMvc.perform(post("/member/add").param("name", "Lazy Boy").param("teams", "1").param("teams", "2"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testMemberAddPageWithoutTeam() throws Exception {
		template.execute("TRUNCATE TABLE team_member;");
		template.execute("TRUNCATE TABLE teams;");
		mockMvc.perform(get("/member/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testMemberEditPage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/member/edit/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("edit-member-form"))
			.andExpect(model().attributeExists("allTeams"))
			.andExpect(model().attributeExists("member"))
			.andExpect(model().attribute("page", "member"));
		mockMvc.perform(post("/member/edit/{id}", id).param("name", "Lazy Boy").param("teams", "1").param("teams", "2"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testMemberEditPageWithoutTeams() throws Exception {
		final int id = 1;
		template.execute("TRUNCATE TABLE team_member;");
		template.execute("TRUNCATE TABLE teams;");
		mockMvc.perform(get("/member/edit/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testMemberDeletePage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/member/delete/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
}
