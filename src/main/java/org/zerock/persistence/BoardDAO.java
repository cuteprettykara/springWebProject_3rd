package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardDAO {
	public int create(BoardVO vo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public int update(BoardVO vo) throws Exception;
	public int delete(Integer bno) throws Exception;
	public int deleteAll() throws Exception;
	public List<BoardVO> listAll() throws Exception;
	
	/* paging 처리 */
	public List<BoardVO> listPage(int page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging() throws Exception;
	
	/* 동적 SQL 처리 */
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
