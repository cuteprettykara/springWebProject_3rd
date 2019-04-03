package org.zerock.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.zerock.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
		
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "org.zerock.mapper.MemberMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public int insertMember(MemberVO vo) {
		return sqlSession.insert(namespace + ".insertMember", vo);
	}

	@Override
	public MemberVO readMemberById(String userid) {
		return sqlSession.selectOne(namespace + ".readMemberById", userid);
	}

	@Override
	public MemberVO readMemberByPW(String userid, String userpw) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(namespace + ".readMemberByPW", paramMap);
	}

}
