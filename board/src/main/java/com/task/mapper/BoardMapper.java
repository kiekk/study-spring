package com.task.mapper;

import java.util.List;

import com.task.domain.BoardVO;
import com.task.domain.Criteria;

public interface BoardMapper {
	//전체 게시글 가져오기
	public List<BoardVO> getList();
	
	//전체 게시글 가져오기(페이징 처리)
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//전체 게시글 개수 가져오기
	public int getTotal(Criteria cri);
	
	//게시글 수정
	public int update(BoardVO board);
	
	//게시글 삭제
	public int delete(Long bno);
	
	//게시글 등록
	public void insert(BoardVO board);
	
	public void insertSelectKey_bno(BoardVO board);
	
	//게시글 가져오기
	public BoardVO read(Long bno);
}
