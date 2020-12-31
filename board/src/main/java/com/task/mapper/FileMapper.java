package com.task.mapper;

import java.util.List;

import com.task.domain.FilesVO;

public interface FileMapper {
	//전체 파일 가져오기
	public List<FilesVO> getFiles(Long bno);
	
	//파일 추가
	public void insertFile(FilesVO file);
	
	//파일 삭제
	public void deleteFile(Long bno);
}
