package com.task.mapper;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.task.domain.FilesVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FileMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private FileMapper mapper;
	
//	@Test
//	public void testInsert() {
//		log.info("testInsert..");
//		FilesVO file = new FilesVO();
//		file.setBno(124);
//		file.setFilePath("test_filePath");
//		mapper.insertFile(file);
//		
//		log.info(file);
//	}
	
//	@Test
//	public void testGetFiles() {
//		mapper.getFiles(124L).forEach(log::info);
//	}
	
//	@Test
//	public void testGetDetail() {
//		log.info("testGetDetail..");
//		String filePath = "test_filePath";
//		log.info(mapper.getDetail(filePath));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info("testDelete..");
//		mapper.deleteFile(124L);
//	}
}
