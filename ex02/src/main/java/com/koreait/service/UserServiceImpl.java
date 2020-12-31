package com.koreait.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.domain.LoginDTO;
import com.koreait.domain.UserVO;
import com.koreait.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {

	@Setter(onMethod_ = @Autowired)
	private UserMapper mapper;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return mapper.login(dto);
	}

	@Override
	public void keepLogin(String id, String sessionId, Date next) throws Exception {
		mapper.keepLogin(id, sessionId, next);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) throws Exception {
		return mapper.checkUserWithSessionKey(value);
	}
	
}
