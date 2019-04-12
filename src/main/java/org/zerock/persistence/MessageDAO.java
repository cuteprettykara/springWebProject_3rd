package org.zerock.persistence;

import org.zerock.domain.MessageVO;

public interface MessageDAO {
	public int create(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(Integer mno) throws Exception;
	
	public int updateState(Integer mno) throws Exception;
}
