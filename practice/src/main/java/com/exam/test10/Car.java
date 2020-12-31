package com.exam.test10;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	String model;	//모델명
	Engine	engine;	//엔진
	Tire[]	tires;	//타이어
	Map<String, Object> options;	//선택사항
}
