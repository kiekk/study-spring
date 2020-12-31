package com.koreait.service;

import com.koreait.domain.Criteria;
import com.koreait.domain.ReplyPageDTO;
import com.koreait.domain.ReplyVO;

public interface ReplyService {
	//댓글 등록
	public int register(ReplyVO reply);
	
	//댓글 가져오기
	public ReplyVO get(Long rno);
	
	//댓글 수정
	public int modify(ReplyVO reply);
	
	//댓글 삭제
	public int remove(Long rno);
	
	//모든 댓글 가져오기
	public ReplyPageDTO getList(Criteria cri, Long bno);
	
}
