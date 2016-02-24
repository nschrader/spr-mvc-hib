package com.sprhib.dao;

import java.util.List;

import com.sprhib.model.Member;

public interface MemberDAO {
	
	public void addMember(Member team);
	public void updateMember(Member team);
	public Member getMember(int id);
	public void deleteMember(int id);
	public List<Member> getMembers();
	public List<Member> getMembersByTeam(int id);

}
