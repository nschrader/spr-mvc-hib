package com.sprhib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import java.util.List;

@Entity
@Table(name="members")
public class Member {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="team_member",
		joinColumns=@JoinColumn(name="id_member", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="id_team", referencedColumnName="id")
	)
	private List<Team> teams;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
