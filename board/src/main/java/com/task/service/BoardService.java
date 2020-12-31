package com.task.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.task.domain.BoardVO;
import com.task.domain.Criteria;
import com.task.domain.FilesVO;

public interface BoardService {
	//전체 게시글 가져오기
	//public List<BoardVO> getList();
	
	//전체 게시글 가져오기(페이징 처리)
	public List<BoardVO> getList(Criteria cri);
	
	//전체 게시글 개수 가져오기
	public int getTotal(Criteria cri);
	
	//특정 게시글 가져오기
	public BoardVO get(Long bno);
	
	//게시글 수정
	public boolean modify(BoardVO board, MultipartFile[] uploadFiles);
	
	//게시글 등록
	public void register(BoardVO board, MultipartFile[] uploadFiles);
	
	//게시글 삭제
	public boolean remove(Long bno);
	
	//전체 파일 가져오기
	public List<FilesVO> getFiles(Long bno);
}
