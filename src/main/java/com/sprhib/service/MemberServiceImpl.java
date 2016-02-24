package com.sprhib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprhib.dao.MemberDAO;
import com.sprhib.model.Member;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	public void addMember(Member member) {
		memberDAO.addMember(member);		
	}

	public void updateMember(Member member) {
		memberDAO.updateMember(member);
	}

	public Member getMember(int id) {
		return memberDAO.getMember(id);
	}

	public void deleteMember(int id) {
		memberDAO.deleteMember(id);
	}

	public List<Member> getMembers() {
		return memberDAO.getMembers();
	}

	public List<Member> getMembersByTeam(int id) {
		return memberDAO.getMembersByTeam(id);
	}
}
