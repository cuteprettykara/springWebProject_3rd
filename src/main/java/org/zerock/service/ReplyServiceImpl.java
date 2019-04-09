package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;

	@Override
	public int addReply(ReplyVO vo) throws Exception {
		return dao.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public int modifyReply(ReplyVO vo) throws Exception {
		return dao.update(vo);
	}

	@Override
	public int removeReply(Integer rno) throws Exception {
		return dao.delete(rno);
	}

}
