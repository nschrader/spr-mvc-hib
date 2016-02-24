package com.sprhib.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import liquibase.integration.spring.SpringLiquibase;

import com.sprhib.init.WebAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=WebAppConfig.class)
@TestPropertySource("classpath:applicationTest.properties")
public class LiquiBaseTest extends JUnitBase {

	@Autowired
	private SpringLiquibase springLiquibase;
	
	private JdbcTemplate template;
	
	private void checkDatabase(JdbcTemplate t) throws Exception {
		assertEquals(t.queryForList("SHOW COLUMNS FROM teams;").size(), 4);
		assertEquals(t.queryForList("SHOW COLUMNS FROM organizations;").size(), 2);
		assertEquals(t.queryForList("SHOW COLUMNS FROM members;").size(), 2);
		assertEquals(t.queryForList("SHOW COLUMNS FROM team_member;").size(), 2);
	}
		
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
	 * As we have an autowired WebApplicationContext that instantiated WebAppConfig
	 * we have already applied all changes to our DB with Liquibase. Let's check if it
	 * is correctly conigurated!
	 * SpringLiquibase implemantation seems crappy for testing, so we have to call afterPropertiesSet()
	 * to make it update our database.
	 */
	@Test
	public void testDatabase() throws Exception {
		checkDatabase(template);
		// Test hibnatedb.changelog-master-1.xml
		template.execute("DROP TABLE teams");
		springLiquibase.afterPropertiesSet();
		checkDatabase(template);
		// Test hibnatedb.changelog-master-2.xml
		template.execute("DROP TABLE members");
		springLiquibase.afterPropertiesSet();
		checkDatabase(template);
	}
}
