package com.task.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.task.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@Test
//	public void testGetList() {
//		service.getList().forEach(log::info);
//	}
	
//	@Test
//	public void testGet() {
//		Long bno = 2L;
//		BoardVO board = service.get(bno);
//		assertNotNull(board);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void testModify() {
//		Long bno = 2L;
//		BoardVO board = new BoardVO();
//		board.setBno(bno);
//		board.setTitle("수정 제목2");
//		board.setContent("수정 내용2");
//		board.setWriter("user03");
//		
//		assertNotNull(service.get(bno));
//		
//		if(service.modify(board)) 
//			log.info("수정한 게시글 : " + service.get(bno));
//		
//	}
	
//	@Test
//	public void testDelete() {
//		Long bno = 2L;
//		
//		BoardVO board = service.get(bno);
//		assertNotNull(board);
//		
//		if(service.remove(bno))
//			log.info("삭제한 게시글 : " + board);
//	}
}
