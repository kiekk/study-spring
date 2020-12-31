package com.exam.test10;

import java.util.Date;
import java.util.Properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tire {
	String maker;	//제조사
	Properties spec;	//규격
	Date createdDate;	//제조일
}
