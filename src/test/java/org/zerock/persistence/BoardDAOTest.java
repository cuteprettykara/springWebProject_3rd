package org.zerock.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/root-context.xml")
public class BoardDAOTest {
	
	public static final int TEST_BNO = 1;

	@Inject
	private BoardDAO dao;
	
	@Test
	public void create() throws Exception {
		BoardVO vo = new BoardVO(2, "타이틀2", "타이틀2 내용", "prettykara");
		assertThat(dao.create(vo), is(1));
	}
	
	@Test
	public void read() throws Exception {
		BoardVO vo = new BoardVO(TEST_BNO, "타이틀1", "타이틀1 내용", "prettykara");
		
		BoardVO actual = dao.read(TEST_BNO);
		assertEquals(vo, actual);
	}
	
	@Test
	public void update() throws Exception {
		BoardVO vo = new BoardVO(TEST_BNO, "타이틀test", "타이틀test 내용", "prettykara");
		assertThat(dao.update(vo), is(1));
		
		BoardVO actual = dao.read(TEST_BNO);
		assertEquals(vo, actual);
	}
	
	@Test
	public void delete() throws Exception {
		assertThat(dao.delete(TEST_BNO), is(1));
		
		assertNull(dao.read(TEST_BNO));
	}
	
	@Test
	public void listAll() throws Exception {
		dao.deleteAll();
		
		assertThat(dao.listAll().size(), is(0));
		
		BoardVO vo = new BoardVO(TEST_BNO, "타이틀1", "타이틀1 내용", "prettykara");
		dao.create(vo);
		
		vo = new BoardVO(2, "타이틀2", "타이틀2 내용", "prettykara");
		dao.create(vo);
		
		assertThat(dao.listAll().size(), is(2));
	}

}
