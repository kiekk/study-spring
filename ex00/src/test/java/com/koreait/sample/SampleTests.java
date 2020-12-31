package com.koreait.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*
 * spring-test 모듈을 이용해서 스프링을 가동시키고 스프링 동작을 활성화합니다.
 * 반드시 JUnit 4.10이상 버전을 사용해야 합니다.(버전이 다르면 Exception 발생)
 */

@RunWith(SpringJUnit4ClassRunner.class)	/* 테스트 코드가 스프링을 실행 */
/* 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록합니다. */
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j /* 로그를 기록하는 Logger를 변수로 생성합니다. */
/* System.out.println()은 Stream이기 때문에 Log를 대신 사용합니다. */
public class SampleTests {
	@Setter(onMethod_ = {@Autowired})
	private Restaurant restaurant;
	
	@Test	/* JUnit에서 테스트 대상을 표시합니다. */
	public void testExist() {
		assertNotNull(restaurant);	/* restaurant가 null이 아니어야만 테스트가 성공 */
		
		log.info(restaurant);
		log.info("=======================");
		log.info(restaurant.getChef());
	}
}
