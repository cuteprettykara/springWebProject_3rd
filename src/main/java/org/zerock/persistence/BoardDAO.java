package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardDAO {
	public int create(BoardVO vo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public int update(BoardVO vo) throws Exception;
	public int delete(Integer bno) throws Exception;
	public int deleteAll() throws Exception;
	public List<BoardVO> listAll() throws Exception;
}
