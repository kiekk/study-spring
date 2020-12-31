package com.task.mapper;

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
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		assertNotNull(mapper.getList());
		log.info(mapper.getList());
	}
	
//	@Test
//	public void testRead() {
//		Long bno = 1L;
//		assertNotNull(mapper.read(bno));
//		log.info(mapper.read(bno));
//	}
	
//	@Test
//	public void testUpdate() {
//		Long bno = 1L;
//		BoardVO board = new BoardVO();
//		board.setBno(bno);
//		board.setTitle("수정 제목");
//		board.setContent("수정 내용");
//		board.setWriter("user01");
//		
//		if(mapper.read(bno) == null) {
//			log.info("없는 번호 입니다. : " + bno);
//			return;
//		}
//		int count = mapper.update(board);
//		log.info("COUNT : " + count);
//	}
	
//	@Test
//	public void testDelete() {
//		Long bno = 75L;
//		
//		if(mapper.read(bno) == null) {
//			log.info("없는 번호 입니다. : " + bno);
//			return;
//		}
//		int count = mapper.delete(bno);
//		log.info("COUNT : " + count);
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성한 글 제목");
//		board.setContent("새로 작성한 글 내용");
//		board.setWriter("newUser");
//
//		mapper.insert(board);
//
//		log.info(board);
//	}
	
//	@Test
//	public void testInsertSelectKey() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성한 글 제목2");
//		board.setContent("새로 작성한 글 내용2");
//		board.setWriter("newUser2");
//		
//		mapper.insertSelectKey_bno(board);
//		
//		log.info(board);
//	}
}
