package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public int create(ReplyVO vo) throws Exception;

	public int update(ReplyVO vo) throws Exception;

	public int delete(Integer rno) throws Exception;
	
	/* paging 처리 */
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	public int countPaging(Integer bno) throws Exception;
}
