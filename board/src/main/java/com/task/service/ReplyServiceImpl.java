package com.task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.domain.Criteria;
import com.task.domain.ReplyPageDTO;
import com.task.domain.ReplyVO;
import com.task.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO reply) {
		log.info("register...");
		return mapper.insert(reply);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get...");
		return mapper.get(rno);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove...");
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("modify...");
		return mapper.update(reply);
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, Long bno) {
		log.info("getList...");
		//return mapper.getListWithPaging(cri, bno);
		return new ReplyPageDTO(mapper.getTotal(bno), mapper.getListWithPaging(cri, bno));
	}
	
}
