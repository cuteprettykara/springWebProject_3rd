package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {	
	public void regist(BoardVO vo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public int modify(BoardVO vo) throws Exception;
	public int remove(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int getCountPaging() throws Exception;
}
