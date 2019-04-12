package org.zerock.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	private static final String namespace = "org.zerock.mapper.MessageMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int create(MessageVO vo) throws Exception {
		return sqlSession.insert(namespace + ".create", vo);
	}

	@Override
	public MessageVO readMessage(Integer mno) throws Exception {
		return sqlSession.selectOne(namespace + ".readMessage", mno);
	}

	@Override
	public int updateState(Integer mno) throws Exception {
		return sqlSession.update(namespace + ".updateState", mno);
	}
}
