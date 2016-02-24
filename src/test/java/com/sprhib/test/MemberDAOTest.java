package com.sprhib.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
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
import com.sprhib.model.Member;
import com.sprhib.model.Team;
import com.sprhib.dao.TeamDAO;
import com.sprhib.dao.MemberDAO;
import com.sprhib.dao.MemberDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=WebAppConfig.class)
@TestPropertySource("classpath:applicationTest.properties")
@Transactional
public class MemberDAOTest extends JUnitBase {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private MemberDAO memberDAO;
	
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
	 * Tests also getMembers
	 */
	@Test
	public void addMemberTest() throws Exception {
		Member testMember = new Member();
		testMember.setName("Linus Torvalds");
		testMember.setTeams(new ArrayList<Team>(teamDAO.getTeams()));
		int size = memberDAO.getMembers().size();
		memberDAO.addMember(testMember);
		List<Member> members = memberDAO.getMembers();
		assertEquals(members.size(), size+1);
		Member m = members.get(size);
		assertEquals(m.getName(), testMember.getName());
	}
	
	@Test
	public void getDatabaseMember() throws Exception {
		Member m = memberDAO.getMember(1);
		assertEquals(m.getName(), "Elon Musk");
		assertEquals(m.getTeams().get(0).getId(), (Integer) 1);
	}
	
	@Test
	public void deleteDatabaseMember() throws Exception {
		int size = memberDAO.getMembers().size();
		memberDAO.deleteMember(1);
		assertEquals(memberDAO.getMembers().size(), size-1);
	}
	
	@Test
	public void updateDatabaseMember() throws Exception {
		int size = memberDAO.getMembers().size();
		Member m = memberDAO.getMember(1);
		m.setName("Bad Member");
		memberDAO.updateMember(m);
		assertEquals(memberDAO.getMembers().size(), size);
		assertEquals(memberDAO.getMember(1).getName(), m.getName());
	}
	
	@Test 
	public void getDatabaseMembersByTeam() throws Exception {
		assertEquals(memberDAO.getMembersByTeam(1).size(), 2);
		assertEquals(memberDAO.getMembersByTeam(2).size(), 1);
	}
}
