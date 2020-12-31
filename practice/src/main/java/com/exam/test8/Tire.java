package com.exam.test8;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tire {
	String maker;	//제조사
	String spec;	//규격
	Date createdDate;	//제조일
}
