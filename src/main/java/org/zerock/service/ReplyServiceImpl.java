package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDao;
	@Inject
	private BoardDAO boardDao;

	@Transactional
	@Override
	public int addReply(ReplyVO vo) throws Exception {
		boardDao.updateReplyCnt(vo.getBno(), 1);
		return replyDao.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return replyDao.list(bno);
	}

	@Override
	public int modifyReply(ReplyVO vo) throws Exception {
		return replyDao.update(vo);
	}

	@Transactional
	@Override
	public int removeReply(Integer rno) throws Exception {
		int bno = replyDao.getBno(rno);
		boardDao.updateReplyCnt(bno, -1);
		return replyDao.delete(rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		return replyDao.listPage(bno, cri);
	}

	@Override
	public int countPaging(Integer bno) throws Exception {
		return replyDao.countPaging(bno);
	}

}
