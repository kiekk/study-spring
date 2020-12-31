package com.koreait.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Shop {
	
	@Setter(onMethod_ = @Autowired)
	private Employee employee;
}
