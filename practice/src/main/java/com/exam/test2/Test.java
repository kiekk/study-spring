package com.exam.test2;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/exam/test2/beans.xml");
		
		System.out.println("[컨테이너에 보관된 객체의 이름 출력]");

		Arrays.asList(ctx.getBeanDefinitionNames()).forEach(System.out::println);
		
		System.out.println("[score2의 별명 출력]");
		Arrays.asList(ctx.getAliases("score2")).forEach(System.out::println);
		
		//name="score"
		Score score = (Score) ctx.getBean("score");
		
		//name="score2,score3,score4"
		Score score2 = (Score) ctx.getBean("score2");
		Score score3 = (Score) ctx.getBean("score3");
		Score score4 = (Score) ctx.getBean("score4");
		
		//name="score-ok!"
		Score scoreOk = (Score) ctx.getBean("score-ok!");
		
		System.out.println("[빈 꺼내기]");
		if(score != null) System.out.println("score 찾았음");
		if(score2 != null) System.out.println("score2 찾았음");
		if(score3 != null) System.out.println("score3 찾았음");
		if(score4 != null) System.out.println("score4 찾았음");
		if(scoreOk != null) System.out.println("score-ok! 찾았음");
		
		System.out.println("[생성된 빈 비교]");
		if(score == score2) System.out.println("score == score2");
		if(score2 == score3) System.out.println("score2 == score3");
		if(score3 == score4) System.out.println("score3 == score4");
		if(score4 == scoreOk) System.out.println("score4 == scoreOk");
	}
}
