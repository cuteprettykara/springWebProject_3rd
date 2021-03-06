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
	
	/* 댓글 카운트 증가 또는 감소 */
	public int updateReplyCnt(Integer bno, int amount) throws Exception;
	
	/* 조회 카운트 증가 */
	public int updateViewCnt(Integer bno) throws Exception;
	
	/* 파일 첨부  */
	public void addAttach(String fullName) throws Exception;
	public List<String> getAttach(Integer bno) throws Exception;
	public void deleteAttach(Integer bno) throws Exception;
	public void replaceAttach(String fullName, Integer bno) throws Exception;
}
