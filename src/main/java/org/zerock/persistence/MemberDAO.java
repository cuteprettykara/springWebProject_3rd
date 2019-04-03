package org.zerock.persistence;

import org.zerock.domain.MemberVO;

public interface MemberDAO {
	public String getTime();
	public int insertMember(MemberVO vo);
	public MemberVO readMemberById(String userid);
	public MemberVO readMemberByPW(String userid, String userpw);
}
