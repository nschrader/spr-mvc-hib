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
import com.sprhib.model.Organization;
import com.sprhib.dao.OrganizationDAO;
import com.sprhib.dao.OrganizationDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=WebAppConfig.class)
@TestPropertySource("classpath:applicationTest.properties")
@Transactional
public class OrganizationDAOTest extends JUnitBase {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
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
	 * Tests also getOrganizations
	 */
	@Test
	public void addOrganizationTest() throws Exception {
		Organization o = new Organization();
		o.setName("Google");
		int size = organizationDAO.getOrganizations().size();
		organizationDAO.addOrganization(o);
		List<Organization> os = organizationDAO.getOrganizations();
		assertEquals(os.size(), size+1);
		assertEquals(o.getName(), os.get(size).getName());
	}
	
	@Test
	public void getDatabaseTeam() throws Exception {
		Organization o = organizationDAO.getOrganization(1);
		assertEquals(o.getName(), "YoMomma");
	}
	
	@Test
	public void deleteDatabaseTeam() throws Exception {
		int size = organizationDAO.getOrganizations().size();
		organizationDAO.deleteOrganization(1);
		assertEquals(organizationDAO.getOrganizations().size(), size-1);
	}
	
	@Test
	public void updateDatabaseTeam() throws Exception {
		int size = organizationDAO.getOrganizations().size();
		Organization o = organizationDAO.getOrganization(1);
		o.setName("Yahoo!");
		organizationDAO.updateOrganization(o);
		assertEquals(organizationDAO.getOrganizations().size(), size);
		assertEquals(organizationDAO.getOrganization(1).getName(), o.getName());
	}
}
