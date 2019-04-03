package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public void regist(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public int modify(BoardVO vo) throws Exception {
		return dao.update(vo);
	}

	@Override
	public int remove(Integer bno) throws Exception {
		return dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

}
