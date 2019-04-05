package org.zerock.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentsBuilderTest {
	private static final Logger log = LoggerFactory.getLogger(UriComponentsBuilderTest.class);

	@Test
	public void testURI() {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
					.path("/board/read")
					.queryParam("bno", 12)
					.queryParam("perPageNum", 20)
					.build();
		
		log.info("/board/read?bno=12&perPageNum=20");
		log.info(uriComponents.toString());
					
	}
	
	@Test
	public void testURI2() {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
					.path("/{module}/{page}")
					.queryParam("bno", 12)
					.queryParam("perPageNum", 20)
					.build()
					.expand("board", "read")
					.encode();
		
		log.info("/board/read?bno=12&perPageNum=20");
		log.info(uriComponents.toString());
					
	}

}
