package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	
	public int addReply(ReplyVO vo) throws Exception;

	public List<ReplyVO> listReply(Integer bno) throws Exception;

	public int modifyReply(ReplyVO vo) throws Exception;

	public int removeReply(Integer rno) throws Exception;
	
	/* paging 처리 */
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	public int countPaging(Integer bno) throws Exception;
}
