package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Transactional
	@Override
	public void regist(BoardVO vo) throws Exception {
		dao.create(vo);
		
		String[] files = vo.getFiles();
		
		if (files == null) return;
		
		for (String fileName : files) {
			dao.addAttach(fileName);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Transactional
	@Override
	public int modify(BoardVO vo) throws Exception {
		Integer bno = vo.getBno();
		
		dao.deleteAttach(bno);
		
		String[] files = vo.getFiles();
		
		if (files != null) {
			for (String fileName : files) {
				dao.replaceAttach(fileName, bno);
			}			
		}
		
		return dao.update(vo);
	}

	@Transactional
	@Override
	public int remove(Integer bno) throws Exception {
		dao.deleteAttach(bno);
		return dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int getCountPaging() throws Exception {
		return dao.countPaging();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return dao.getAttach(bno);
	}

}
