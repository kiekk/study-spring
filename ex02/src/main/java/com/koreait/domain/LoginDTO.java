package com.koreait.domain;

import lombok.Data;

@Data
public class LoginDTO {
	private String id;
	private String password;
	private boolean useCookie;
}
