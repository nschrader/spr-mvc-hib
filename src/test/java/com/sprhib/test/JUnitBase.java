package com.sprhib.test;

import static org.junit.Assert.*;

import org.springframework.jdbc.core.JdbcTemplate;
import liquibase.integration.spring.SpringLiquibase;
import com.sprhib.model.Team;
import com.sprhib.model.Organization;

/*
 * An inhereited class is surely not the best way to avoid code redundancy, but
 * is increadibly simple! A JUnit rule takes twice the amount of code...
 */
public class JUnitBase {
	protected JdbcTemplate database(SpringLiquibase l) {
		return new JdbcTemplate(l.getDataSource());
	}
	
	protected void databasePrepare(JdbcTemplate template) {
		databaseClean(template);
		template.execute("INSERT INTO organizations (name) VALUES ('YoMomma');");
		template.execute("INSERT INTO organizations (name) VALUES ('QWERTY');");
		template.execute("INSERT INTO teams (name, rating, fk_organization) VALUES ('BestTeamEver', '20', '1');");
		template.execute("INSERT INTO teams (name, rating, fk_organization) VALUES ('AvarageTeam', '10', '1');");
		template.execute("INSERT INTO members (name) VALUES ('Elon Musk');");
		template.execute("INSERT INTO members (name) VALUES ('Freddy Mercury');");
		template.execute("INSERT INTO team_member (id_team, id_member) VALUES ('1', '1');");
		template.execute("INSERT INTO team_member (id_team, id_member) VALUES ('1', '2');");
		template.execute("INSERT INTO team_member (id_team, id_member) VALUES ('2', '2');");
	}
	
	protected void databaseClean(JdbcTemplate template) {
		template.execute("TRUNCATE TABLE teams;");
		template.execute("TRUNCATE TABLE organizations;");
		template.execute("TRUNCATE TABLE members;");
		template.execute("TRUNCATE TABLE team_member;");
	}
	
	protected Team teamCreate() {
		Team t = new Team();
		t.setName("EpicTeam");
		t.setRating(99);
		t.setOrganization(new Organization());
		t.getOrganization().setName("Bad Ass");
		return t;
	}
	
	protected void assertTeamsEqual(Team t1, Team t2) throws Exception {
		assertEquals(t1.getName(), t2.getName());
		assertEquals(t1.getRating(), t2.getRating());
		assertEquals(t1.getOrganization().getName(), t2.getOrganization().getName());
	}
}
