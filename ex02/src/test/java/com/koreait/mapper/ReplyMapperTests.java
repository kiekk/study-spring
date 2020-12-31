package com.koreait.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.koreait.domain.Criteria;
import com.koreait.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//SpringRunner는 SpringJUnit4ClassRunner의 자식이며 4.3버전 이상부터 사용 가능한 확장판입니다.
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	private Long[] arBno = {34892L, 34893L, 34894L, 34895L, 34896L};
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	@Test
//	public void testInsert() {
//		//5개 최신 게시글에 2개씩 댓글 달기
//		//IntStream
//		log.info("testInsert....");
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			ReplyVO reply = new ReplyVO();
//			
//			//01234 01234
//			reply.setBno(arBno[i % 5]);
//			reply.setReply("댓글 내용 : " + i);
//			reply.setReplyer("replyer " + i);
//			
//			mapper.insert(reply);
//		});
//	}
	
//	@Test
//	public void testRead() {
//		log.info("testRead...");
//		Long rno = 1L;
//		log.info(mapper.read(rno));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info("testDelete...");
//		Long rno = 2L;
//		log.info(mapper.delete(rno));
//	}
	
//	@Test
//	public void testUpdate() {
//		log.info("testUpdate...");
//		ReplyVO reply = new ReplyVO();
//		reply.setRno(1L);
//		reply.setReply("수정 댓글");
//		log.info(mapper.update(reply));
//	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> list = mapper.getListWithPaging(cri, arBno[0]);
		
		list.forEach(log::info);
	}
}
