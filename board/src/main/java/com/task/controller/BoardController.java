package com.task.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.domain.BoardVO;
import com.task.domain.Criteria;
import com.task.domain.FilesVO;
import com.task.domain.PageDTO;
import com.task.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	
	//게시글 목록 페이지
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	//게시글 등록 페이지
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {}
	
	//게시글 등록 처리
	@PostMapping("/register")
	public String register(@RequestParam("file") MultipartFile[] uploadFiles, BoardVO board, RedirectAttributes rttr) {
		System.out.println("register... " + board);
		service.register(board, uploadFiles);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	
	//게시글 상세보기, 수정 페이지
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		System.out.println("get..." + bno);
		model.addAttribute("board", service.get(bno));
		model.addAttribute("files", service.getFiles(bno));
	}
	
	//게시글 수정 처리
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, @RequestParam("file") MultipartFile[] uploadFiles, RedirectAttributes rttr) {
		System.out.println("modify..." + board);
		rttr.addFlashAttribute("result", service.modify(board, uploadFiles) ? "success" : "fail");
		return "redirect:/board/get" + cri.getListLink() + "&bno=" + board.getBno();
	}
	
	//게시글 삭제 처리
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		System.out.println("remove... " + bno);
		rttr.addFlashAttribute("result", service.remove(bno) ? "success" : "fail");
		return "redirect:/board/list" + cri.getListLink();
	}
	
	//파일 다운로드
	@GetMapping("/fileDownload")
	public void download(FilesVO file, HttpServletResponse response) throws IOException {
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Users\\soonho\\Desktop\\it\\Spring\\workspace\\board\\src\\main\\webapp\\resources\\files\\"+file.getFilePath())); 
		response.setContentType("application/octet-stream"); 
		response.setContentLength(fileByte.length); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getFileName(),"UTF-8")+"\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.getOutputStream().write(fileByte); 
		response.getOutputStream().flush(); 
		response.getOutputStream().close();
	}
}
