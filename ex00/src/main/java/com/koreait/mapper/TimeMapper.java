package com.koreait.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public interface TimeMapper {
	@Select("SELECT SYSDATE FROM DUAL")
	public String getTime();
	
	//실제 SQL은 XML을 이용해서 처리
	public String getTime2();
}
