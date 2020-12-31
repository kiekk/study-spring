package com.task.service;

import com.task.domain.Criteria;
import com.task.domain.ReplyPageDTO;
import com.task.domain.ReplyVO;

public interface ReplyService {
	//댓글 등록
	public int register(ReplyVO reply);
	
	//댓글 가져오기
	public ReplyVO get(Long rno);
	
	//댓글 삭제
	public int remove(Long rno);
	
	//댓글 수정
	public int modify(ReplyVO reply);
	
	//댓글 목록 가져오기
	public ReplyPageDTO getList(Criteria cri, Long bno);
	
}
