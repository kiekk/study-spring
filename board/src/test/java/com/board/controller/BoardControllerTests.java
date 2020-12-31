package com.board.controller;


import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;

import com.task.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
//	@Test
//	public void testGetList() throws Exception {
//		log.info("testGetList...");
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
//				.andReturn()
//				.getModelAndView()
//				.getModelMap());
//	}
	
//	@Test
//	public void testRead() throws Exception {
//		log.info("testGet...");
//		String bno = "1";
//		BoardVO board = (BoardVO) mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
//				.param("bno", bno))
//				.andReturn().getModelAndView().getModelMap().get("board");
//		assertNotNull(board);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void testModify() throws Exception {
//		log.info("testModify...");
//		String bno = "3";
//		FlashMap fMap = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", bno)
//				.param("title", "수정 제목3")
//				.param("content", "수정 내용3")
//				.param("writer", "user04"))
//		.andReturn().getFlashMap();
//		
//		String viewName = fMap.getTargetRequestPath();
//		String result = (String) fMap.get("result");
//		
//		log.info("result : " + result + "\tviewName : " + viewName);
//	}
	
//	@Test
//	public void testDelete() throws Exception {
//		log.info("testDelete...");
//		String bno = "3";
//		FlashMap fMap = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", bno))
//		.andReturn().getFlashMap();
//		
//		String viewName = fMap.getTargetRequestPath();
//		String result = (String) fMap.get("result");
//		
//		log.info("result : " + result + "\tviewName : " + viewName);
//	}
}
