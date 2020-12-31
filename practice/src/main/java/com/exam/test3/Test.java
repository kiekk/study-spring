package com.exam.test3;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/exam/test3/beans.xml");
		
		System.out.println("[컨테이너에 보관된 객체의 이름 출력]");

		Arrays.asList(ctx.getBeanDefinitionNames()).forEach(System.out::println);
		
		System.out.println("[score2의 별명 출력]");
		Arrays.asList(ctx.getAliases("score2")).forEach(System.out::println);

		Score score1 = (Score) ctx.getBean("com.exam.test3.Score");
		Score score2 = (Score) ctx.getBean("com.exam.test3.Score#0");

		if(score1 == score2) System.out.println("score == score#0");
		
		Score score3 = (Score) ctx.getBean("com.exam.test3.Score#1");
		if(score1 != score3) System.out.println("score != score#1");
		
	}
}
