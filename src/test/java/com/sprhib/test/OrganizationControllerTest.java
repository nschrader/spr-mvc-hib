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
public class OrganizationControllerTest extends JUnitBase {
	
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
	public void testOrganizationListPage() throws Exception {
		final String[] urls =  {"/organization", "/organization/list"};
		for (String s: urls) {
			mockMvc.perform(get(s))
				.andExpect(status().isOk())
				.andExpect(view().name("list-of-organizations"))
				.andExpect(model().attributeExists("organizations"))
				.andExpect(model().attribute("page", "organization"));
		}
	}
	
	@Test
	public void testOrganizationAddPage() throws Exception {
		mockMvc.perform(get("/organization/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-organization-form"))
			.andExpect(model().attributeExists("organization"))
			.andExpect(model().attribute("page", "organization"));
		mockMvc.perform(post("/organization/add").param("name", "Google"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testOrganizationEditPage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/organization/edit/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("edit-organization-form"))
			.andExpect(model().attributeExists("organization"))
			.andExpect(model().attribute("page", "organization"));
		mockMvc.perform(post("/organization/edit/{id}", id).param("name", "Yahoo"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
	
	@Test
	public void testOrganizationDeletePage() throws Exception {
		final int id = 1;
		mockMvc.perform(get("/organization/delete/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("page", "home"));
	}
}
