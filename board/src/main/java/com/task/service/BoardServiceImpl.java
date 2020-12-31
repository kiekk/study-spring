package com.task.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.task.domain.BoardVO;
import com.task.domain.Criteria;
import com.task.domain.FilesVO;
import com.task.mapper.BoardMapper;
import com.task.mapper.FileMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private FileMapper f_mapper;
	
	//전체 게시글 가져오기
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("BoardServiceImpl.getList...");
		return mapper.getListWithPaging(cri);
	}
	
	//게시글 가져오기
	@Override
	public BoardVO get(Long bno) {
		log.info("BoardServiceImpl.get..." + bno);
		return mapper.read(bno);
	}
	//게시글 수정
	@Override
	public boolean modify(BoardVO board, MultipartFile[] uploadFiles) {
		log.info("BoardServiceImpl.modify..." + board);
		deleteFile(board.getBno());		//폴더에 저장된 기존 파일 삭제
		f_mapper.deleteFile(board.getBno());	//DB에 저장된 파일 삭제
		uploadFile(board.getBno(), uploadFiles);	//지정된 경로와 DB에 새로 업로드된 파일 추가
		return mapper.update(board) != 0;
	}
	
	//게시글 등록
	@Override
	public void register(BoardVO board, MultipartFile[] uploadFiles) {
		log.info("BoardServiceImpl.register..." + board);
		mapper.insertSelectKey_bno(board);
		log.info("uploadFile...");
		uploadFile(board.getBno(), uploadFiles);
	}
	
	//게시글 삭제
	@Override
	public boolean remove(Long bno) {
		log.info("BoardServiceImpl.remove..." + bno);
		deleteFile(bno);	//폴더에 있는 파일 삭제
		f_mapper.deleteFile(bno);	//DB에 있는 파일 삭제
		return mapper.delete(bno) != 0;
	}
	
	//게시글 총 개수 가져오기
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	
	//파일 DB에 저장
	public void uploadFile(Long bno, MultipartFile[] uploadFiles) {
		log.info("BoardServiceImpl.register...");
		List<String> saveFileNames;
		try {
			//업로드된 파일을 지정된 경로에 저장한 뒤 filePath를 배열로 받아옵니다.
			saveFileNames = saveFile(uploadFiles);
			for(int i=0;i<uploadFiles.length;i++) {
				//만약 넘어온 file 태그가 비어있다면 continue;
				if(uploadFiles[i].isEmpty()) continue;
				FilesVO file = new FilesVO();
				file.setBno(bno);
				//Original Name
				file.setFileName(uploadFiles[i].getOriginalFilename());
				//저장된 경로의 Path
				file.setFilePath(saveFileNames.get(i));
				//DB에 파일 추가
				f_mapper.insertFile(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//경로에 있는 파일 삭제
	public void deleteFile(Long bno) {
		String savePath = "C:\\Users\\soonho\\Desktop\\it\\Spring\\workspace\\board\\src\\main\\webapp\\resources\\files";
		List<FilesVO> files = getFiles(bno);
		for(FilesVO file : files) {
			File temp = new File(savePath + "\\" + file.getFilePath());
			//해당 경로에 파일이 있다면 삭제
			if(temp.exists()) temp.delete();
		}
	}
	
	//파일 경로에 저장
	public List<String> saveFile(MultipartFile[] uploadFiles) throws IOException {
		String uploadPath = "C:\\Users\\soonho\\Desktop\\it\\Spring\\workspace\\board\\src\\main\\webapp\\resources\\files";
		List<String> uploadFileNames = new ArrayList<>();
		for(MultipartFile uploadFile : uploadFiles) {
			//만약 넘어온 file 태그가 비어있다면 continue;
			if(uploadFile.isEmpty()) continue;
			// 파일 이름 변경
			UUID uuid = UUID.randomUUID();
			String saveName = uuid + "_" + uploadFile.getOriginalFilename();
			
			log.info("saveName: " + saveName);
			
			// 저장할 File 객체를 생성(껍데기 파일)
			File saveFile = new File(uploadPath, saveName); // 저장할 폴더 이름, 저장할 파일 이름
			FileCopyUtils.copy(uploadFile.getBytes(), saveFile);	//덮어쓰기
			uploadFile.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
			uploadFileNames.add(saveName);
		}
		return uploadFileNames;
	}
	
	//전체 파일 가져오기
	@Override
	public List<FilesVO> getFiles(Long bno) {
		log.info("BoardServiceImpl.getFiles : " + bno);
		return f_mapper.getFiles(bno);
	}
}
