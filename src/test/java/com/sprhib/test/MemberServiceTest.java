package com.sprhib.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sprhib.dao.MemberDAO;
import com.sprhib.service.MemberService;
import com.sprhib.service.MemberServiceImpl;
import com.sprhib.model.Member;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
	
	@Mock
	private MemberDAO memberDAO;
	
	@InjectMocks
	private MemberService memberService = new MemberServiceImpl();
	
	private Member testMember;
	
	@Before
	public void init() {
		testMember = new Member();
		testMember.setName("Chuck Norris");
		List<Member> testMemberList = new ArrayList<Member>();
		testMemberList.add(testMember);
		when(memberDAO.getMember(anyInt())).thenReturn(testMember);
		when(memberDAO.getMembers()).thenReturn(testMemberList);
		when(memberDAO.getMembersByTeam(anyInt())).thenReturn(testMemberList);
	}
	
	@Test
	public void getMemberTest() throws Exception {
		assertEquals(memberService.getMember(1).getName(), testMember.getName());
		verify(memberDAO, times(1)).getMember(anyInt());
	}
	
	@Test
	public void addMemberTest() throws Exception {
		memberService.addMember(new Member());
		verify(memberDAO, times(1)).addMember(any(Member.class));
	}
	
	@Test
	public void getMembersTest() throws Exception {
		assertEquals(memberService.getMembers().size(), 1);
		verify(memberDAO, times(1)).getMembers();
	}
	
	@Test
	public void updateMemberTest()throws Exception  {
		memberService.updateMember(new Member());
		verify(memberDAO, times(1)).updateMember(any(Member.class));
	}
	
	@Test
	public void deleteMemberTest() throws Exception {
		memberService.deleteMember(1);
		verify(memberDAO, times(1)).deleteMember(anyInt());
	}
	
	@Test
	public void getMembersByTeamTest() throws Exception {
		assertEquals(memberService.getMembersByTeam(1).size(), 1);
		verify(memberDAO, times(1)).getMembersByTeam(anyInt());
	}
}