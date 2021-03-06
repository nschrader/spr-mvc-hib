package com.sprhib.service;

import java.util.List;

import com.sprhib.model.Team;

public interface TeamService {
	
	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id) throws Exception;
	public List<Team> getTeams();
	public List<Team> getTeamsByOrganization(int id);

}
