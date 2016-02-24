package com.sprhib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprhib.dao.TeamDAO;
import com.sprhib.dao.MemberDAO;
import com.sprhib.model.Team;
import com.sprhib.model.Member;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private MemberDAO memberDAO;

	public void addTeam(Team team) {
		teamDAO.addTeam(team);		
	}

	public void updateTeam(Team team) {
		teamDAO.updateTeam(team);
	}

	public Team getTeam(int id) {
		return teamDAO.getTeam(id);
	}

	public void deleteTeam(int id) throws Exception {
		if (memberDAO.getMembersByTeam(id).isEmpty())
			teamDAO.deleteTeam(id);
		else
			throw new Exception();
	}

	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}

	public List<Team> getTeamsByOrganization(int id) {
		return teamDAO.getTeamsByOrganization(id);
	}
}
