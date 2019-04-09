package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
	
	public int addReply(ReplyVO vo) throws Exception;

	public List<ReplyVO> listReply(Integer bno) throws Exception;

	public int modifyReply(ReplyVO vo) throws Exception;

	public int removeReply(Integer rno) throws Exception;
}
