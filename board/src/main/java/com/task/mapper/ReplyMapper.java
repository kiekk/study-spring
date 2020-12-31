package com.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.task.domain.Criteria;
import com.task.domain.ReplyVO;

public interface ReplyMapper {
	//댓글 등록
	public int insert(ReplyVO reply);
	
	//댓글 가져오기
	public ReplyVO get(Long rno);
	
	//댓글 삭제
	public int delete(Long rno);
	
	//댓글 수정
	public int update(ReplyVO reply);
	
	//댓글 목록 가져오기
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	//총 댓글 개수 가져오기
	public int getTotal(Long bno);
}
