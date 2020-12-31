package com.koreait.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;
import com.koreait.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//주입은 반드시 가장 위에서부터 아래로 내려오듯이 주입해야 하기 때문에
//구현한 클래스가 아닌 틀을 제공하는 인터페이스를 통해서 주입합니다.

@Log4j
@Service	//서비스 객체임을 표시
@AllArgsConstructor	//모든 파라미터를 이용하는 생성자를 선언
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {	
		log.info("register..." + board);
		mapper.insertSelectKey_bno(board);
	}

	//조회 작업의 구현과 테스트
	@Override
	public BoardVO get(Long bno) {
		log.info("get..." + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..." + board);
		//return (mapper.update(board) != 0) ? true : false;
		//결국 (mapper.update(board) != 0)의 값이 true false이기 때문에 true, false를 작성안해줘도 됩니다.
		return mapper.update(board) != 0;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("delete..." + bno);
		//결국 (mapper.delete(board) != 0)의 값이 true false이기 때문에 true, false를 작성안해줘도 됩니다.
		//return (mapper.delete(bno) != 0) ? true : false;
		return mapper.delete(bno) != 0;
	}

	//목록작업의 구현과 테스트
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList..." + cri);
		return mapper.getListWithPaging(cri);
	}
	
	//게시글 전체 개수 가져오기
	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal...");
		return mapper.getTotal(cri);
	}
	
}
