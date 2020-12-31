package com.koreait.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;

import com.koreait.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration	//Servlet의 ServletContext를 이용하기 위함
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	//메모리에 Context 영역을 할당해주는 ApplicationContext 객체가 먼저 필요합니다.
	//Servlet을 사용하기 위한 궁극적인 목적으로 servlet-context.xml파일의 경로를 설정해 준 후
	//build된 서블릿을 MockMvc 객체에 담아줍니다.
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext wac;
	
	//가짜 MVC
	//마치 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행해 볼 수 있습니다.
	private MockMvc mockMvc;
	
	@Before	//Before가 적용된 메소드는 모든 테스트 전에 매번 실행됩니다.
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
//	@Test
//	public void testRemove() throws Exception {
//		String bno = "1";
//		String result = (String) mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", bno))
//			   .andReturn()
//			   .getFlashMap().get("result");
//		
//		log.info(result);
//	}
	
//	@Test
//	public void testModify() throws Exception {
//		String bno = "1";
//		//FlashMap 가져오기, redirect의 경우 ModelMap이 새로 생성되기 때문에 ModelMap으로는 값을 가져올 수 없습니다.
//		FlashMap map = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", bno)
//				.param("title", "수정한 글 제목33")
//				.param("content", "수정한 글 내용 33")
//				.param("writer", "user00"))
//				.andReturn()
//				.getFlashMap();
//		//URL 경로
//		String resultPage = map.getTargetRequestPath();
//		//결과
//		String result = (String) map.get("result");
//		
//		log.info("resultPage : " + resultPage + "\tresult : " + result);
//	}
	
//	@Test
//	public void testRead() throws Exception {
//		String bno = "1";
//		BoardVO board = (BoardVO) mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
//											.param("bno", bno))
//										 .andReturn()
//										 .getModelAndView()
//										 .getModelMap().get("board");
//		assertNotNull(board);
//		log.info("board : " + board);			
//	}
	
//	@Test
//	public void testRegister() throws Exception {
//		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새 글 제목")
//				.param("content", "테스트 새 글 내용")
//				.param("writer", "hds1204"))
//			   .andReturn()
//			   .getModelAndView()
//			   .getViewName();
//		log.info(result);
//	}

//	@Test
//	public void testList() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
//				.param("pageNum", "2")
//				.param("amount", "10"))//GET방식으로 URL 호출 후 응답(return)
//		.andReturn()	//반환된 결과를 이용해서
//		.getModelAndView()	//Model에 어떤 데이터가 담겨 있는지
//		.getModelMap());	//Map형식으로 확인
//	}
	
	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "1")
				.param("amount", "10")
				.param("type", "TC")
				.param("keyword", "한동석"))//GET방식으로 URL 호출 후 응답(return)
		.andReturn()	//반환된 결과를 이용해서
		.getModelAndView()	//Model에 어떤 데이터가 담겨 있는지
		.getModelMap());	//Map형식으로 확인
	}
	
}
