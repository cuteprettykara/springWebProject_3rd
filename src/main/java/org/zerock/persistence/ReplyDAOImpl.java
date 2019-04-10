package org.zerock.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	private static final String namespace = "org.zerock.mapper.ReplyMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return sqlSession.selectList(namespace + ".list", bno);
	}

	@Override
	public int create(ReplyVO vo) throws Exception {
		return sqlSession.insert(namespace + ".create", vo);
	}

	@Override
	public int update(ReplyVO vo) throws Exception {
		return sqlSession.insert(namespace + ".update", vo);
	}

	@Override
	public int delete(Integer rno) throws Exception {
		return sqlSession.insert(namespace + ".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		return sqlSession.selectList(namespace + ".listPage", paramMap);
	}

	@Override
	public int countPaging(Integer bno) throws Exception {
		return sqlSession.selectOne(namespace + ".countPaging", bno);
	}



}
