package org.zerock.service;

import javax.inject.Inject;

import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageDAO messageDao;
	@Inject
	private PointDAO pointDao;

	@Override
	public void addMessage(MessageVO vo) throws Exception {
		messageDao.create(vo);
		pointDao.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		messageDao.updateState(mno);
		pointDao.updatePoint(uid, 5);
		
		return messageDao.readMessage(mno);
	}

}
