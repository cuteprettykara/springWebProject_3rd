package org.zerock.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.ReplyVO;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/root-context.xml")
public class ReplyDAOTest {
	private static final Logger log = LoggerFactory.getLogger(ReplyDAOTest.class);
	
	@Inject
	private ReplyDAO dao;

	@Test
	public void list() throws Exception {
		ReplyVO vo = new ReplyVO(BoardDAOTest.TEST_BNO, "댓글 내용1", "prettykara");
		dao.create(vo);
		
		vo = new ReplyVO(BoardDAOTest.TEST_BNO, "댓글 내용2", "prettykara");
		dao.create(vo);
		
		assertThat(dao.list(BoardDAOTest.TEST_BNO).size(), is(2));
	}
	
	@Test
	public void create() throws Exception {
		ReplyVO vo = new ReplyVO(BoardDAOTest.TEST_BNO, "댓글 내용1", "prettykara");
		
		assertThat(dao.create(vo), is(1));
	}	
}
