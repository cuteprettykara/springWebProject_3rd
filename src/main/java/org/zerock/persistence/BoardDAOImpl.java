package org.zerock.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

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

	@Override
	public int countPaging() throws Exception {
		return sqlSession.selectOne(namespace + ".countPaging");
	}
	
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public int updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		return sqlSession.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public int updateViewCnt(Integer bno) throws Exception {
		return sqlSession.update(namespace + ".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		sqlSession.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return sqlSession.selectList(namespace + ".getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		sqlSession.delete(namespace + ".deleteAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> paramMap  = new HashMap<String, Object>();
		paramMap.put("fullName", fullName);
		paramMap.put("bno", bno);
		
		sqlSession.insert(namespace + ".replaceAttach", paramMap);
	}
}
