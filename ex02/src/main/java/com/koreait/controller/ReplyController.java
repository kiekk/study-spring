package com.koreait.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.domain.Criteria;
import com.koreait.domain.ReplyPageDTO;
import com.koreait.domain.ReplyVO;
import com.koreait.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


/*
 * REST (Representational State Transfer)
 * 하나의 URI는 하나의 고유한 리소스를 대표하도록 설계됩니다.
 * 예) /board/123 : 게시물 중 123번
 */
@RestController
@RequestMapping("/replies/")
@Log4j
public class ReplyController {
	
	
	//필드의 setter로 주입해도 되고, @AllArgsConstructor 어노테이션으로 주입해도 무방
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
//	//댓글 등록
//	//브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴합니다.
//	//consumes와 produces를 통해서 JSON방식의 데이터만 처리하도록 합니다.
//	
//	@PostMapping(value="/new", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
//	//@RequestBody를 적용하여 JSON데이터를 어노테이션 뒤에 있는 타입으로 반환하도록 지정합니다.
//	public ResponseEntity<String> create(@RequestBody ReplyVO reply) {
//		//ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입
//		log.info("ReplyVO : " + reply);
//		int insertCount = service.register(reply);
//		log.info("Reply Insert Count : " + insertCount);
//		
//		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : 
//			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@PostMapping(value="/new", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		log.info("create...");
		int result = service.register(reply);
		log.info("Reply Insert Count : " + result);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : 
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		log.info("getList...");
		
		return new ResponseEntity<>(service.getList(new Criteria(page, 10), bno), HttpStatus.OK);
	}
	
	//댓글 조회
	//GetMapping
	//XML, JSON으로 결과 확인
	//url은 rno만 전달합니다.
	@GetMapping(value="/{rno}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get...");
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	//댓글 수정
	//PUT	: 자원의 전체 수정, 자원 내 모든 필드를 전달해야 합니다. 일부만 전달할 경우 전달되지 않은 필드는 모두 초기화 처리가 됩니다.
	//		: 만약 전체 데이터를 전송하지 않아도 쿼리문ㅇ세ㅓ 필요한 데이터가 아니라면 이상이 없습니다.
	//PATCH	: 자원의 일부 수정, 수정할 필드만 전달하면 됩니다.
	//PUT과 PATCH 모두 사용
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}", 
			consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody ReplyVO reply){
		reply.setRno(rno);		
		log.info("modify...");
		log.info("rno : " + rno);
		
		return service.modify(reply) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : 
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//삭제
	//DeleteMapping 사용
	//value는 수정, 조회와 동일
	//produces만 존재합니다.
	//성공일 때 문자열 결과 전송
	@DeleteMapping(value="/{rno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove..." + rno);
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
