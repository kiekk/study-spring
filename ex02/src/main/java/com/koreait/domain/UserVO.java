package com.koreait.domain;

import lombok.Data;

@Data
public class UserVO {
	private String id;
	private String password;
	private String name;
	private int point;
}
