package org.zerock.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class MemberDAOTest {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOTest.class);
	
	public static final String TEST_USER = "prettykara";
	
	@Inject
	private MemberDAO dao;

	@Test
	public void testTime() {
		log.info(dao.getTime());
	}
	
	@Test
	@Rollback
	public void testInsertMember() throws Exception {
		MemberVO vo = new MemberVO("userId", "1111", "name", "userId@gmail.com");
		assertThat(dao.insertMember(vo), is(1));
	}
	
	@Test
	public void readMemberById() throws Exception {
		MemberVO member = new MemberVO(TEST_USER, "1111", "남상범", "prettykara@gmail.com");
		assertEquals(member, dao.readMemberById(TEST_USER));
	}

	@Test
	public void readMemberByPW() throws Exception {
		MemberVO member = new MemberVO(TEST_USER, "1111", "남상범", "prettykara@gmail.com");
		assertEquals(member, dao.readMemberByPW(TEST_USER, "1111"));
	}

}
