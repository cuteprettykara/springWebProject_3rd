package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace = "org.zerock.mapper.BoardMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int create(BoardVO vo) throws Exception {
		return sqlSession.insert(namespace + ".create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return sqlSession.selectOne(namespace + ".read", bno);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		return sqlSession.update(namespace + ".update", vo);
	}

	@Override
	public int delete(Integer bno) throws Exception {
		return sqlSession.delete(namespace + ".delete", bno);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return sqlSession.delete(namespace + ".deleteAll");
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace + ".listCriteria", cri);
	}
}
