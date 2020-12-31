package com.exam.test9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
	String maker;	//제조사
	int cc;	//배기량
}
