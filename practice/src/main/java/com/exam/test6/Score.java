package com.exam.test6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
	String name;
	float kor;
	float eng;
	float math;
	
	public float average() {
		return sum() / (float)3;
	}
	
	public float sum() {
		return kor + eng + math;
	}
}
