package com.koreait.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testGetTotal() {
		log.info("TOTAL COUNT : " + service.getTotal(new Criteria()));
	}
	
	//게시글 삭제하기
//	@Test
//	public void testDelete() {
//		Long bno = 2L;
//		BoardVO board = service.get(bno);
//		
//		//전달받은 bno에 해당하는 게시글의 유무 검사
//		assertNotNull(board);
//		
//		if(service.remove(bno)) 
//			log.info("삭제한 게시글 : " + board);	
//	}
	
	//게시글 수정하기
//	@Test
//	public void testModify() {
//		Long bno = 2L;
//		BoardVO board = new BoardVO();
//		board.setBno(bno);
//		board.setTitle("수정한 글 제목3");
//		board.setContent("수정한 글 내용3");
//		board.setWriter("user00");
//
//		//전달받은 bno에 해당하는 게시글의 유무 검사
//		assertNotNull(service.get(bno));
//		
//		//기존 게시글 정보 출력
//		log.info("기존 글 : " + service.get(bno));
//		
//		//게시글 수정
//		//수정된 게시글 정보 출력
//		if(service.modify(board)) 
//			log.info("수정한 글 : " + service.get(bno));
//	}
	
	//해당 게시글 정보 가져오기
//	@Test
//	public void testGet() {
//		Long bno = 2L;
//		BoardVO board = service.get(bno);
//		//전달받은 board에 해당하는 게시글의 유무 검사
//		assertNotNull(board);
//		log.info(board);
//	}
	
	//게시글 목록 전체 조회
//	@Test
//	public void testGetList() {
//		//Collection.forEach(선언부 -> 사용부);
//		//람다식
//		//service.getList().forEach(board -> log.info(board));
//		
//		//메소드 참조
//		service.getList().forEach(log::info);
//	}
	
//	@Test
//	public void testGetList() {
//		service.getList(new Criteria(1, 10)).forEach(log::info);
//	}
	
	//게시글 생성
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성하는 글5");
//		board.setContent("새로 작성하는 내용5");
//		board.setWriter("hds1204");
//		
//		service.register(board);
//		
//		log.info("생성된 게시물의 번호 : " + board.getBno());
//	}

//	@Test
//	public void testExist() {
//		assertNotNull(service);
//		
//		log.info("SERVICE : " + service);
//	}
	
}
